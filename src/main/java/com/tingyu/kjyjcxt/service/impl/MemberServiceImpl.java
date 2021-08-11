package com.tingyu.kjyjcxt.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tingyu.kjyjcxt.dao.MemberMapper;
import com.tingyu.kjyjcxt.dto.MemberDTO;
import com.tingyu.kjyjcxt.entity.MemberEntity;
import com.tingyu.kjyjcxt.service.LogService;
import com.tingyu.kjyjcxt.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.concurrent.CompletableFuture;

/**
 * @Author essionshy
 * @Create 2021/5/24 23:08
 * @Version kjyjcxt
 */
@Service
@Slf4j
public class MemberServiceImpl extends ServiceImpl<MemberMapper, MemberEntity> implements MemberService {

    @Resource
    private MemberMapper memberMapper;

    @Resource
    private LogService logService;


    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    @Override
    public void insert(MemberDTO dto) throws Exception {

        //保存会员基本信息

        long startTime = System.currentTimeMillis();

        CompletableFuture.runAsync(() -> {
            saveMemberInfo(dto);
        });


        //保存操作日志, 如果出现异常，当前方法不捕获异常，则会导致回滚

       /* try{
            logService.saveLog();


        }catch (Exception e){
            log.error("执行日志保存操作记录出现异常，但与业务不重要，因此不必让其他执行成功的事务回滚");
        }*/
        //saveLog();
        CompletableFuture.runAsync(() -> {
            logService.saveLog();
        }).whenComplete((v, e) -> {


        });

        long endTime = System.currentTimeMillis();

        log.info("执行耗时：{}", endTime - startTime);

    }

    private void saveMemberInfo(MemberDTO dto) {
        if (dto == null) {
            return;
        }
        MemberEntity entity = new MemberEntity();
        BeanUtils.copyProperties(dto, entity);
        boolean success = this.save(entity);
        if (success) {
            log.info("保存会员信息成功,memberId:" + dto.getId());
        } else {
            log.info("保存会员信息失败,memberId:" + dto.getId());

        }

    }


}
