package backend.sellerB.controller;

import backend.sellerB.dto.OrderDetailDto;
import backend.sellerB.dto.RegisterOrderDetailDto;
import backend.sellerB.service.OrderDetailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/orderdetail")
public class OrderDetailController {
    private final OrderDetailService orderDetailService;

    public OrderDetailController(OrderDetailService orderDetailService) {
        this.orderDetailService = orderDetailService;
    }

    @PostMapping
    public ResponseEntity<OrderDetailDto> saveOrderDetail(@Valid @RequestBody RegisterOrderDetailDto registerOrderDetailDto) {
        return ResponseEntity.ok(orderDetailService.createOrderDetail(registerOrderDetailDto));
    }

    @GetMapping("/{seq}")
    public ResponseEntity<OrderDetailDto> getOrderDetailDetail(@PathVariable Long seq) {
        return ResponseEntity.ok(orderDetailService.getOrderDetailDetail(seq));
    }

    @GetMapping("/list/{orderSeq}")
    public ResponseEntity<List<OrderDetailDto>> getOrderDetailbyOrderSeq(@PathVariable Long orderSeq) {
        return ResponseEntity.ok(orderDetailService.getOrderDetailbyOrderSeq(orderSeq));
    }
    @GetMapping("/list")
    public ResponseEntity<List<OrderDetailDto>> getOrderDetailList(HttpServletRequest request) {
        return ResponseEntity.ok(orderDetailService.getOrderDetailList());
    }

    @PutMapping("/{seq}")
    public ResponseEntity<OrderDetailDto> updateOrderDetail(@Valid @RequestBody OrderDetailDto orderDetailDto, @PathVariable Long seq) {
        return ResponseEntity.ok(orderDetailService.updateOrderDetail(seq, orderDetailDto));
    }

    @DeleteMapping("/{seq}")
    public ResponseEntity<OrderDetailDto> deleteOrderDetail(@PathVariable Long seq) {
        return ResponseEntity.ok(orderDetailService.deleteOrderDetail(seq));
    }
}
