package com.free.order.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 账户远程feign接口
 *
 * @author dinghao
 * @date 2021/3/8
 */
@FeignClient(name = "account-service")
public interface AccountFeignClient {

    @PostMapping("account/reduce")
    Boolean reduce(@RequestParam("userId") String userId, @RequestParam("money") Integer money);
}
