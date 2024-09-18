package com.api_transaction.transaction.infra.feign;

import com.api_transaction.transaction.app.supply.dto.StockRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "${stock.service.name}", url = "${stock.service.url}", configuration = FeignClientInterceptor.class)
public interface StockFeignClient {
    @PostMapping("${stock.service.url.increase}")
    void updateStock(@RequestBody StockRequest stockRequest);
}
