package com.xuliucar.carlocation.event

import com.xuliucar.carlocation.bean.CarsListBean

data class LocationEvent(var flag: Boolean, var data: CarsListBean.DataBean.InfoBean)