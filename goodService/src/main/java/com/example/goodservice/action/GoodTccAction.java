//package com.example.goodservice.action;
//
//import com.example.comonbeans.entity.good.Good;
//import io.seata.rm.tcc.api.BusinessActionContext;
//import io.seata.rm.tcc.api.BusinessActionContextParameter;
//import io.seata.rm.tcc.api.LocalTCC;
//import io.seata.rm.tcc.api.TwoPhaseBusinessAction;
//
//@LocalTCC
//public interface GoodTccAction {
//    @TwoPhaseBusinessAction(name = "goodTccAction")
//    boolean prepareSaveGood(BusinessActionContext businessActionContext, @BusinessActionContextParameter("id") String id, Good good);
//    boolean commit(BusinessActionContext businessActionContext);
//    boolean rollback(BusinessActionContext businessActionContext);
//}
