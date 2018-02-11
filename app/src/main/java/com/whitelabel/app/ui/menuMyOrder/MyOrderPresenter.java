package com.whitelabel.app.ui.menuMyOrder;

import com.whitelabel.app.WhiteLabelApplication;
import com.whitelabel.app.adapter.SearchFilterAdapter;
import com.whitelabel.app.bean.OrderBody;
import com.whitelabel.app.data.service.IBaseManager;
import com.whitelabel.app.data.service.ICommodityManager;
import com.whitelabel.app.model.ApiFaildException;
import com.whitelabel.app.model.CategoryBaseBean;
import com.whitelabel.app.model.GOUserEntity;
import com.whitelabel.app.model.MyAccountOrderInner;
import com.whitelabel.app.model.ResponseModel;
import com.whitelabel.app.model.SearchFilterResponse;
import com.whitelabel.app.ui.RxPresenter;
import com.whitelabel.app.ui.search.SearchContract;
import com.whitelabel.app.utils.ErrorHandlerAction;
import com.whitelabel.app.utils.ExceptionParse;
import com.whitelabel.app.utils.RxUtil;
import com.whitelabel.app.utils.logger.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by img on 2018/1/26.
 */

public class MyOrderPresenter extends RxPresenter<MyOrderContract.View> implements MyOrderContract.Presenter {
    private ICommodityManager iCommodityManager;
    private IBaseManager iBaseManager;

    @Inject
    public MyOrderPresenter(ICommodityManager iCommodityManager,IBaseManager iBaseManager) {
        this.iCommodityManager = iCommodityManager;
        this.iBaseManager=iBaseManager;
    }

    @Override
    public void getShoppingCount() {
        Subscription  subscription=  iCommodityManager.getLocalShoppingProductCount()
            .compose(RxUtil.<Integer>rxSchedulerHelper())
            .subscribe(new Subscriber<Integer>() {
                @Override
                public void onCompleted() {
                }
                @Override
                public void onError(Throwable e) {

                }
                @Override
                public void onNext(Integer integer) {
                    getShoppingCartCount(integer);
                }
            });
        addSubscrebe(subscription);
    }

    private void getShoppingCartCount(int count){
        if(iBaseManager.isSign()) {
            count= (int) (iBaseManager.getUser().getCartItemCount()+count);
        }
        mView.loadShoppingCount(count);
    }

    @Override
    public void saveShoppingCartCount(int num) {
        if (iBaseManager.isSign()) {
            GOUserEntity userEntity = iBaseManager.getUser();
            userEntity.setCartItemCount(num);
            iBaseManager.saveUser(userEntity);
        }
    }

    @Override
    public void setToCheckout(ArrayList<OrderBody> orderBodies) {
        HashMap<String, String> params=new HashMap<>();
        String sessionKey=iBaseManager.getUser().getSessionKey();
        String orderId="";
        params.put("session_key",sessionKey);

        if(orderBodies!=null&&!orderBodies.isEmpty()){
            for (int i=0;i<orderBodies.size();i++){
                orderId=orderBodies.get(i).getOrderId();
                params.put("products["+i+"][item_id]", orderBodies.get(i).getItemId());
                params.put("products["+i+"][qty]", orderBodies.get(i).getOrderQuantity());
            }
        }
        params.put("order_id",orderId);

        mView.showProgressDialog();
        Subscription  subscription = iCommodityManager.setToCheckout(params).compose(RxUtil.<ResponseModel>rxSchedulerHelper()).subscribe(

            new Subscriber<ResponseModel>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable throwable) {
                    mView.closeProgressDialog();
                    ApiFaildException exception=ExceptionParse.parseException(throwable);
                    if(exception.getErrorType()== ExceptionParse.ERROR.HTTP_ERROR){
                        mView.showNetErrorMessage();
                    }else if(exception.getErrorType()==ExceptionParse.ERROR.API_ERROR){
                        mView.showFaildMessage(exception.getErrorMsg());
                    }
                }

                @Override
                public void onNext(ResponseModel responseModel) {
                    mView.closeProgressDialog();
                    if (responseModel.getStatus()==1){
                        mView.showReorderSuccessMessage();
                    }else {
                        mView.showReorderErrorMessage(responseModel.getErrorMessage());
                    }
                }
            });
        addSubscrebe(subscription);

    }

    @Override
    public void setToCheckoutDetail(String orderId,List<MyAccountOrderInner> orderBodies) {
        HashMap<String, String> params=new HashMap<>();
        String sessionKey=iBaseManager.getUser().getSessionKey();
        params.put("session_key",sessionKey);

        if(orderBodies!=null&&!orderBodies.isEmpty()){
            for (int i=0;i<orderBodies.size();i++){
                params.put("products["+i+"][item_id]", orderBodies.get(i).getItemId());
                params.put("products["+i+"][qty]", orderBodies.get(i).getQty());
            }
        }
        params.put("order_id",orderId);

        mView.showProgressDialog();
        Subscription  subscription = iCommodityManager.setToCheckout(params).compose(RxUtil.<ResponseModel>rxSchedulerHelper()).subscribe(

            new Subscriber<ResponseModel>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable throwable) {
                    mView.closeProgressDialog();
                    ApiFaildException exception=ExceptionParse.parseException(throwable);
                    if(exception.getErrorType()== ExceptionParse.ERROR.HTTP_ERROR){
                        mView.showNetErrorMessage();
                    }else if(exception.getErrorType()==ExceptionParse.ERROR.API_ERROR){
                        mView.showFaildMessage(exception.getErrorMsg());
                    }
                }

                @Override
                public void onNext(ResponseModel responseModel) {
                    mView.closeProgressDialog();
                    if (responseModel.getStatus()==1){
                        mView.showReorderSuccessMessage();
                    }else {
                        mView.showReorderErrorMessage(responseModel.getErrorMessage());
                    }
                }
            });
        addSubscrebe(subscription);
    }


}
