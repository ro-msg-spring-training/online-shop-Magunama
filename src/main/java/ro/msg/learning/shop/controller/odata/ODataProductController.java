package ro.msg.learning.shop.controller.odata;


import com.sap.cloud.sdk.service.prov.api.operations.*;
import com.sap.cloud.sdk.service.prov.api.request.*;
import com.sap.cloud.sdk.service.prov.api.response.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ro.msg.learning.shop.model.Product;
import ro.msg.learning.shop.repository.ProductRepository;

import java.util.List;

@Component
@Transactional
@RequiredArgsConstructor
public class ODataProductController {
    private final ProductRepository repository;

    @Read(serviceName = "ProductService", entity = "Product")
    public ReadResponse read(ReadRequest request) {
        Product product = this.repository.findById((Integer) request.getKeys().get("id")).orElse(null);
        if (product != null) {
            return ReadResponse.setSuccess().setData(product).response();
        } else {
            return ReadResponse.setError(ErrorResponse.getBuilder().setStatusCode(404).response());
        }
    }

    @Query(serviceName = "ProductService", entity = "Product")
    public QueryResponse query(QueryRequest request) {
        return QueryResponse.setSuccess().setData((List<Product>) this.repository.findAll()).response();
    }

    @Create(serviceName = "ProductService", entity = "Product")
    public CreateResponse create(CreateRequest request) {
        Product product = this.repository.save(request.getDataAs(Product.class));
        return CreateResponse.setSuccess().setData(product).response();
    }

    @Update(serviceName = "ProductService", entity = "Product")
    public UpdateResponse update(UpdateRequest request) {
        Product product = this.repository.findById((Integer) request.getKeys().get("id")).orElse(null);
        if (product != null) {
            product.setName((String) request.getMapData().get("name"));
            // todo: add other fields
            return UpdateResponse.setSuccess().response();
        } else {
            return UpdateResponse.setError(ErrorResponse.getBuilder().setStatusCode(404).response());
        }
    }

    @Delete(serviceName = "ProductService", entity = "Product")
    public DeleteResponse delete(DeleteRequest request) {
        this.repository.deleteById((Integer) request.getKeys().get("id"));
        return DeleteResponse.setSuccess().response();
    }
}