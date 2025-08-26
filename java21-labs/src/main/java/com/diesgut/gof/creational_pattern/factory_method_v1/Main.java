package com.diesgut.gof.creational_pattern.factory_method_v1;

import com.diesgut.gof.creational_pattern.factory_method_v1.creator.BankOfficer;
import com.diesgut.gof.creational_pattern.factory_method_v1.creator.CheckingAccountBankOfficer;
import com.diesgut.gof.creational_pattern.factory_method_v1.creator.MortgageLaonBankOfficer;
import com.diesgut.gof.creational_pattern.factory_method_v1.creator.SavingsAccountBankOfficer;

public class Main {
    public static void main(String[] args) {
        /*
         * Este patrón de diseño Factory Method se utiliza para crear objetos sin especificar la clase exacta del objeto que se creará.
         * Proporciona una interfaz para crear objetos en una superclase, pero permite a las subclases alterar el tipo de objetos que se crearán.
         *
         * 1. IBankingProduct: Interfaz común para todos los productos bancarios (por ejemplo, cuentas de ahorro, cuentas corrientes, préstamos hipotecarios).
         * Esta es la interfaz que el "factory method" retornará.
         *
         * 2. SavingsAccount, CheckingAccount, MortgageLoan: Clases concretas que implementan la interfaz IBankingProduct.
         * Representan las implementaciones específicas de los productos bancarios.
         *
         * 3. BankOfficer: Clase creadora abstracta que declara el "factory method", 'createBankingProduct()'.
         * Define un método, 'openProductForClient()', que utiliza el producto creado, desacoplando así la lógica de negocios de la creación de objetos.
         *
         * 4. SavingsAccountBankOfficer, CheckingAccountBankOfficer, MortgageLoanBankOfficer: Subclases concretas de BankOfficer.
         * Cada una implementa el "factory method" 'createBankingProduct()' para crear una instancia específica de un producto bancario.
         *
         * Observación: La clase 'BankOfficer' no tiene como responsabilidad principal la creación del producto.
         * Su lógica de negocio central, como 'openProductForClient()', se basa en el producto, delegando su instanciación a las subclases.
         */

      //  El cliente decide qué "fábrica" (qué subclase de BankOfficer) usar.
        BankOfficer bankOfficer = new SavingsAccountBankOfficer();
        bankOfficer.openProductForClient();

        bankOfficer = new CheckingAccountBankOfficer();
        bankOfficer.openProductForClient();

        bankOfficer = new MortgageLaonBankOfficer();
        bankOfficer.openProductForClient();
    }
}
