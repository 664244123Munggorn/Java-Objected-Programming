public class Inventory {

    private String productName;
    private int stock;

    public Inventory(String productName, int stock) {
        this.productName = productName;
        this.stock = stock;
    }

    public void withdrawStock(int amount)
            throws InsufficientStockException {

        if (amount > stock) {
            throw new InsufficientStockException(
                "สินค้าคงเหลือไม่พอ! คงเหลือ "
                + stock + " ชิ้น แต่ขอเบิก "
                + amount + " ชิ้น"
            );
        }

        stock -= amount;

        System.out.println(
            "เบิกสินค้า " + amount +
            " ชิ้น สำเร็จ คงเหลือ " + stock + " ชิ้น"
        );
    }
}
