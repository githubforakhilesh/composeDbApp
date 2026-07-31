package com.example.newdbapp.Presenter.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newdbapp.Domain.Model.CustomerListData
import com.example.newdbapp.Domain.UseCase.CustomerListUseCase
import com.example.newdbapp.Utility.ListType
import com.example.newdbapp.Utility.ParamsConstant
import com.example.newdbapp.Utility.PreferenceManager
import com.example.newdbapp.Utility.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CustomerListViewModel @Inject constructor(
    private val preferenceManager: PreferenceManager,
    private val customerListUseCase: CustomerListUseCase
) : ViewModel() {
    private val _custListData = MutableStateFlow<CustomerListUiState>(CustomerListUiState.IdleState)
    val custListData: StateFlow<CustomerListUiState> = _custListData.asStateFlow()

    fun fetchUserList() {
        viewModelScope.launch {
            _custListData.value = CustomerListUiState.LoadingState
            val deliveryBoyId = preferenceManager.deliveryBoyId.first()
            val params = mutableMapOf<String, String>()
            params["dboy_action"] = "db_cust_list_new"
            params["d_date"] = "today"
            params["actor_id"] = deliveryBoyId
            params["delivery_slot"] = "Morning-Everyday"
            params["login_id"] = deliveryBoyId
            params["page_no"] = "0"
            params["list_type"] = ListType.TODAY_CUST_LIST_PENDING.type
            
            when(val result = customerListUseCase.invoke(params)) {
                is Resource.Error -> {
                   _custListData.value = CustomerListUiState.ErrorState(result.message)
                }
                Resource.Loading -> {
                    _custListData.value = CustomerListUiState.LoadingState
                }
                is Resource.Success -> {
                    if(result.data != null){
                        if(result.data.status.equals(ParamsConstant.SUCCESS)){
                             _custListData.value = CustomerListUiState.SuccessState(result.data.data?.toDomain())
                        }else{
                            _custListData.value = CustomerListUiState.ErrorState(result.data.msg.toString())
                        }
                    }else{
                        _custListData.value = CustomerListUiState.ErrorState("Data not found")
                    }
                }
            }
        }
    }
}
//modified
sealed class CustomerListUiState{
    data class SuccessState(val customerListData: CustomerListData?):CustomerListUiState()
    data class ErrorState(val message:String):CustomerListUiState()
    object LoadingState : CustomerListUiState()
    object IdleState : CustomerListUiState()
}
