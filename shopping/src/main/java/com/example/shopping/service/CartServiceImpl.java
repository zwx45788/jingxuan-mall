package com.example.shopping.service;

import com.example.shopping.api.CartServiceGrpc;
import com.example.shopping.api.Shopping;
import com.example.shopping.model.Cart;
import com.example.shopping.model.ProductSku;
import com.example.shopping.model.ProductSpu;
import com.example.shopping.repository.CartRepository;
import com.example.shopping.repository.ProductSkuRepository;
import com.example.shopping.repository.ProductSpuRepository;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@GrpcService
public class CartServiceImpl extends CartServiceGrpc.CartServiceImplBase {
    @Autowired
    private CartRepository cartRepository;
    private ProductSkuRepository productSkuRepository;
    private ProductSpuRepository productSpuRepository;

    @Override
    @Transactional
    public void addToCart(Shopping.AddToCartRequest request,
                          StreamObserver<Shopping.AddToCartResponse> responseStreamObserver){
        try{
            long userId = request.getUserId();
            long skuId = request.getSkuId();
            long quantity = request.getQuantity();
            if(quantity == 0) quantity = 1;
            log.info("AddToCart:userId={},skuId={},quantity:{}",userId,skuId,quantity);

            Optional<Cart> existingOpt=cartRepository.findByUserIdAndSkuId(userId,skuId);
            Cart item;
            if(existingOpt.isPresent()){
                item = existingOpt.get();
                item.setQuantity(item.getQuantity()+quantity);
            }else{
                item = Cart.builder()
                        .userId(userId)
                        .skuId(skuId)
                        .quantity(quantity)
                        .build();
            }

            Cart savedItem = cartRepository.save(item);

            responseStreamObserver.onNext(Shopping.AddToCartResponse.newBuilder()
                    .setSuccess(true)
                    .setMessage("已加入购物车")
                    .build());
            responseStreamObserver.onCompleted();
        } catch (Exception e) {
            log.error("AddToCart:{}",e.getMessage());
            responseStreamObserver.onError(Status.INTERNAL.withDescription(e.getMessage()).asRuntimeException());
        }
    }

    @Override
    @Transactional
    public void getCartInfo(Shopping.GetCartInfoRequest request,
                            StreamObserver<Shopping.GetCartInfoResponse> responseStreamObserver){
        try{
            long userId = request.getUserId();
            List<Cart> cartItems = cartRepository.findByUserIdOrderByCreatedAtDesc(userId);
            List<Shopping.CartItem> items = new ArrayList<>();
            for(Cart cartItem : cartItems){
                Optional<ProductSku> skuOpt = productSkuRepository.findById(cartItem.getSkuId());
                if (skuOpt.isEmpty()) continue;
                ProductSku sku = skuOpt.get();
                Optional<ProductSpu> spuOpt = productSpuRepository.findById(sku.getProductId());
                if (spuOpt.isEmpty()) continue;
                ProductSpu spu = spuOpt.get();

                items.add(Shopping.CartItem.newBuilder()
                        .setTitle(spu.getTitle())
                        .setImage(sku.getImage())
                        .setSpecJson(sku.getSpecJson())
                        .setQuantity(cartItem.getQuantity())
                        .setPrice(sku.getPrice())
                        .setChecked(cartItem.getChecked())
                        .setShopId(spu.getShopId())
                        .build()
                );
            }

            responseStreamObserver.onNext(Shopping.GetCartInfoResponse.newBuilder()
                    .setSuccess(true)
                    .setMessage("success")
                    .addAllCartItems(items)
                    .build());
            responseStreamObserver.onCompleted();
        } catch (Exception e){
            log.error("getCartInfo:{}",e.getMessage());
            responseStreamObserver.onError(Status.INTERNAL.withDescription(e.getMessage()).asRuntimeException());
        }

    }

   @Override
   @Transactional
    public void removeFromCart(Shopping.RemoveFromCartRequest request,
                               StreamObserver<Shopping.RemoveFromCartResponse> responseStreamObserver){
        try{
            long userId =  request.getUserId();
            long cartItemId = request.getCartItemId();
            Optional<Cart> existingOpt=cartRepository.findByUserIdAndSkuId(userId,cartItemId);
            if(existingOpt.isEmpty()) return;
            Cart item = existingOpt.get();

            item.setStatus(0);

            cartRepository.save(item);

            responseStreamObserver.onNext(Shopping.RemoveFromCartResponse.newBuilder()
                    .setSuccess(true)
                    .setMessage("删除成功")
                    .build()
            );
            responseStreamObserver.onCompleted();

        }catch(Exception e){
            log.error("removeFromCart:{}",e.getMessage());
            responseStreamObserver.onError(Status.INTERNAL.withDescription(e.getMessage()).asRuntimeException());
        }

    }
}