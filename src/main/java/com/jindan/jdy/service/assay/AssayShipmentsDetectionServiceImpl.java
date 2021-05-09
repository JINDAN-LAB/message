package com.jindan.jdy.service.assay;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jindan.jdy.common.dto.AssayShipmentsDetectionDto;
import com.jindan.jdy.common.mapper.AssayShipmentsDetectionDao;
import com.jindan.jdy.common.pojo.AssayShipmentsDetection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**   
 * @Description:TODO(产品发货信息服务实现)
 *
 * @version: V1.0
 * @author: kong
 * 
 */

@Component
public class AssayShipmentsDetectionServiceImpl  extends ServiceImpl<AssayShipmentsDetectionDao,AssayShipmentsDetection> implements AssayShipmentsDetectionService  {

    @Autowired
    AssayShipmentsDetectionDao assayEntrustBillsDao;

    @Override
    public Page<AssayShipmentsDetection> seleteDepartmentSubfacility(AssayShipmentsDetectionDto departmentSuggestDto) {
        if(departmentSuggestDto.getCurrentPage() <= 0  ){
            departmentSuggestDto.setCurrentPage(1);
        }
        Page<AssayShipmentsDetection> page =new Page<>(departmentSuggestDto.getCurrentPage(),departmentSuggestDto.getPageSize());
        QueryWrapper<AssayShipmentsDetection> queryWrapper =new QueryWrapper<>();
        if( departmentSuggestDto.getFid() !=null &&  !departmentSuggestDto.getFid().isEmpty()  ){
            queryWrapper.eq("fid",departmentSuggestDto.getFid());
        }
        if( departmentSuggestDto.getSuppliesName() !=null && !departmentSuggestDto.getSuppliesName().isEmpty() ){
            queryWrapper.like("supplies_name",departmentSuggestDto.getSuppliesName());
        }
        if( departmentSuggestDto.getInvoiceNumber() !=null && !departmentSuggestDto.getInvoiceNumber().isEmpty() ){
            queryWrapper.like("invoice_number",departmentSuggestDto.getInvoiceNumber());
        }
        if( departmentSuggestDto.getBatchNumber() !=null && !departmentSuggestDto.getBatchNumber().isEmpty() ){
            queryWrapper.like("batch_number",departmentSuggestDto.getBatchNumber());
        }
        if( departmentSuggestDto.getPackGoods() !=null &&  !departmentSuggestDto.getPackGoods().isEmpty() ){
            queryWrapper.like("pack_goods",departmentSuggestDto.getPackGoods());
        }
        if( departmentSuggestDto.getProductStandard() !=null &&  !departmentSuggestDto.getProductStandard().isEmpty() ){
            queryWrapper.like("product_standard",departmentSuggestDto.getProductStandard());
        }
        if( departmentSuggestDto.getNumbers() !=null &&  !departmentSuggestDto.getNumbers().isEmpty() ){
            queryWrapper.like("numbers",departmentSuggestDto.getNumbers());
        }
        if( departmentSuggestDto.getQualityRequire() !=null  &&  !departmentSuggestDto.getQualityRequire().isEmpty() ){
            queryWrapper.like("quality_require",departmentSuggestDto.getQualityRequire());
        }
        if( departmentSuggestDto.getCountryOfDestination() !=null &&  !departmentSuggestDto.getCountryOfDestination().isEmpty() ){
            queryWrapper.like("country_of_destination",departmentSuggestDto.getCountryOfDestination());
        }
        if( departmentSuggestDto.getSalesman() !=null &&  !departmentSuggestDto.getSalesman().isEmpty() ){
            queryWrapper.like("salesman",departmentSuggestDto.getSalesman());
        }
        if( departmentSuggestDto.getPutTime() !=null  &&  !departmentSuggestDto.getPutTime().isEmpty() ){
            queryWrapper.like("put_time",departmentSuggestDto.getPutTime());
        }
        if( departmentSuggestDto.getShipmentsTime() !=null &&  !departmentSuggestDto.getShipmentsTime().isEmpty() ){
            queryWrapper.like("shipments_time",departmentSuggestDto.getShipmentsTime());
        }
        if( departmentSuggestDto.getMeasurement() !=null &&  !departmentSuggestDto.getMeasurement().isEmpty() ){
            queryWrapper.like("measurement",departmentSuggestDto.getMeasurement());
        }
        return (Page<AssayShipmentsDetection>) assayEntrustBillsDao.selectPage(page,queryWrapper);
    }
}