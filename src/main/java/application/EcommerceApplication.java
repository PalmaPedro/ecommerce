package application;

import controllers.EcommerceController;

/**
 * Entry point of the application
 */
public class EcommerceApplication {
    //public EcommerceController ecommerce;
    public static void main( String[] args )
    {
        try{
            EcommerceController ecommerceController = new EcommerceController();
            ecommerceController.startBrowsing();
        }
        catch(Exception e){
            System.out.println("The ecommerce platform is having issues loading");
            System.out.println("Try again later!");
        }
    }
}
