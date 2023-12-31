package com.example.tradeu.ui

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tradeu.MyApplication
import com.example.tradeu.ui.login.LoginViewModel
import com.example.tradeu.ui.mainpage.viewmodels.FavoritesViewModel
import com.example.tradeu.ui.mainpage.viewmodels.HomeViewModel
import com.example.tradeu.ui.mainpage.viewmodels.ProfileViewModel
import com.example.tradeu.ui.mainpage.viewmodels.UploadViewModel
import com.example.tradeu.ui.payment.PaymentViewModel
import com.example.tradeu.ui.productdetail.ProductDetailViewModel
import com.example.tradeu.ui.register.RegisterViewModel
import com.example.tradeu.ui.splash.SplashViewModel
import com.example.tradeu.ui.transaction.TransactionSuccessViewModel

class ViewModelFactory private constructor(
    private val application: MyApplication,
    private val dataStore: DataStore<Preferences>
): ViewModelProvider.NewInstanceFactory(){

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)){
            return LoginViewModel(application.provideUserRepository(dataStore)) as T
        }else if(modelClass.isAssignableFrom(ProfileViewModel::class.java)){
            return ProfileViewModel(application.provideUserRepository(dataStore)) as T
        }else if(modelClass.isAssignableFrom(HomeViewModel::class.java)){
            return HomeViewModel(application.provideUserRepository(dataStore), application.provideItemRepository()) as T
        }else if(modelClass.isAssignableFrom(FavoritesViewModel::class.java)){
            return FavoritesViewModel(application.provideUserRepository(dataStore), application.provideItemRepository()) as T
        }else if(modelClass.isAssignableFrom(UploadViewModel::class.java)){
            return UploadViewModel(application.provideItemRepository(), application.provideUserRepository(dataStore)) as T
        }else if(modelClass.isAssignableFrom(SplashViewModel::class.java)){
            return SplashViewModel(application.provideUserRepository(dataStore)) as T
        }else if (modelClass.isAssignableFrom(ProductDetailViewModel::class.java)){
            return ProductDetailViewModel(application.provideUserRepository(dataStore), application.provideItemRepository()) as T
        }else if(modelClass.isAssignableFrom(PaymentViewModel::class.java)){
            return PaymentViewModel(application.provideUserRepository(dataStore), application.provideItemRepository(), application.provideTransactionRepository()) as T
        }else if (modelClass.isAssignableFrom(TransactionSuccessViewModel::class.java)){
            return TransactionSuccessViewModel(application.provideTransactionRepository()) as T
        }else if(modelClass.isAssignableFrom(RegisterViewModel::class.java)){
            return RegisterViewModel(application.provideUserRepository(dataStore)) as T
        }
        return super.create(modelClass)
    }

    companion object{
        @Volatile
        private var INSTANCE:ViewModelFactory?=null

        fun getInstance(
            application: MyApplication,
            dataStore: DataStore<Preferences>
        ):ViewModelFactory{
            return INSTANCE?: synchronized(this){
                INSTANCE?:ViewModelFactory(application, dataStore)
            }.also { INSTANCE = it }
        }
    }
}