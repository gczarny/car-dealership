package pl.car_dealership.api.controller.rest;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.car_dealership.api.dto.CarPurchaseDTO;
import pl.car_dealership.api.dto.CarToBuyDTO;
import pl.car_dealership.api.dto.CarsToBuyDTO;
import pl.car_dealership.api.dto.InvoiceDTO;
import pl.car_dealership.api.dto.mapper.CarPurchaseMapper;
import pl.car_dealership.api.dto.mapper.InvoiceMapper;
import pl.car_dealership.business.CarPurchaseService;
import pl.car_dealership.domain.CarPurchaseRequest;
import pl.car_dealership.domain.Invoice;

@RestController
@AllArgsConstructor
@RequestMapping(PurchaseRestController.API_PURCHASE)
public class PurchaseRestController {

    static final String API_PURCHASE = "/api/purchase";

    private final CarPurchaseService carPurchaseService;
    private final CarPurchaseMapper carPurchaseMapper;
    private final InvoiceMapper invoiceMapper;

    @GetMapping
    public CarsToBuyDTO carsPurchaseData() {
        return CarsToBuyDTO.builder()
                .carsToBuy(carPurchaseService.availableCars().stream()
                        .map(carPurchaseMapper::map)
                        .toList())
                .build();

    }

    @PostMapping
    public InvoiceDTO makePurchase(@Valid @RequestBody CarPurchaseDTO carPurchaseDTO) {
        CarPurchaseRequest request = carPurchaseMapper.map(carPurchaseDTO);
        Invoice invoice = carPurchaseService.purchase(request);
        return invoiceMapper.map(invoice);
    }
}
