package tech.ian.watchStore.exception;

public class ProductNotFoundExeception extends RuntimeException{

    public ProductNotFoundExeception() {
        super("O produto não foi achado");
    }
}
