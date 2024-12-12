package com.example.userapp.core.base.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.userapp.core.common.MyTimer
import com.example.userapp.core.common.TimerType
import com.example.userapp.data.dto.error.ErrorResponse
import com.example.userapp.core.base.BaseViewEvent
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlin.properties.Delegates

abstract class BaseViewModel <ViewState : BaseViewState, ViewAction : BaseAction>(initialState: ViewState) :
    ViewModel() {

    private val mutexForSendingAction = Mutex()

    private val progressStateObservable: MutableLiveData<ProgressState> by lazy {
        MutableLiveData()
    }

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    private val _baseEvent = MutableLiveData<Event<BaseViewEvent>>()
    val baseEvent: LiveData<Event<BaseViewEvent>> = _baseEvent


    /**
     * For storing ViewState
     */
    val _uiStateFlow = MutableStateFlow(initialState)
    val uiStateFlow = _uiStateFlow.asStateFlow()

    protected var stateTimeTravelDebugger: StateTimeTravelDebugger? = null

    /**
     * Delegate will handle state event deduplication
     * (multiple states of the same type holding the same data
     * will not be dispatched multiple times to LiveData stream)
     */

    protected var state by Delegates.observable(initialState) { _, old, new ->

        viewModelScope.launch {
            _uiStateFlow.emit(new)
        }

        if (new != old) {
            stateTimeTravelDebugger?.apply {
                addStateTransition(old, new)
                //logLast()
            }
        }
    }


    fun sendAction(viewAction: ViewAction) {
        viewModelScope.launch {
            mutexForSendingAction.withLock {
                stateTimeTravelDebugger?.addAction(viewAction)
                state = onReduceState(viewAction)
            }
        }
    }

    protected abstract fun onReduceState(viewAction: ViewAction): ViewState

    fun setLoading(loading: Boolean) = _loading.postValue(loading)

    var myTimer: MyTimer? = null

    fun runTimer(
        onFinish: () -> Unit,
        onTick: (millisUntilFinished: Long) -> Unit,
        millisInFuture: Long,
        countDownInterval: Long = 1,
        timerLifecycle: Boolean = true,
        typeTimer: TimerType = TimerType.OneTimeTimer,
        oneTask: Boolean = true
    ) {
        myTimer = MyTimer(millisInFuture, countDownInterval)
        myTimer?.startTimer(
            {
                onFinish()
            },
            {
                onTick(it)
            },
            typeTimer,
            timerLifecycle,
            oneTask
        )
    }

    override fun onCleared() {
        super.onCleared()
        myTimer?.timerClose()
    }

    open fun handleException(e: ErrorResponse) {
        setLoading(false)
        e.message?.let {
            showCustomError(
                it
            )
        }
    }

    open fun handleInfoMessage(message: String) {
        setLoading(false)
        showInfoMessage(message)
    }

    internal fun emitProgressShow() {
        progressStateObservable.postValue(ProgressState.Show)
    }

    operator fun CompositeDisposable.plusAssign(disposable: Disposable) {
        add(disposable)
    }

    fun showCustomError(message: String) =
        _baseEvent.postValue(Event(BaseViewEvent.ShowCustomError(message)))

    fun showInfoMessage(message: String) =
        _baseEvent.postValue(Event(BaseViewEvent.ShowInfoMessage(message)))


    sealed class ProgressState {
        object Show : ProgressState()
        object Hide : ProgressState()
    }
}
