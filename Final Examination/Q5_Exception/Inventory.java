public class Inventory {
    private Product[] products;

    public Inventory(Product[] products) {
        this.products = products;
    }

    public Product[] getProducts() {
        return products;
    }

    public Product findProduct(String code)
            throws ProductNotFoundException {

        for (Product product : products) {

            if (product.getCode().equalsIgnoreCase(code)) {
                return product;
            }
        }

        throw new ProductNotFoundException(
            "Product " + code + " not found."
        );
    }
}