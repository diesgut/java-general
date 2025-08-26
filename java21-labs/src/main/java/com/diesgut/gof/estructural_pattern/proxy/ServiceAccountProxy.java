package com.diesgut.gof.estructural_pattern.proxy;

// El intermediario que controla el acceso al objeto real.
public class ServiceAccountProxy implements IAccountService {
    private BankAccountService realAccount;
    private final String authenticatedUser;

    public ServiceAccountProxy(BankAccountService realAccount, String authenticatedUser) {
        this.realAccount = realAccount;
        this.authenticatedUser = authenticatedUser;
    }

    // El depósito no requiere una verificación especial, se delega directamente.
    @Override
    public void deposit(double amount) {
        realAccount.deposit(amount);
    }

    @Override
    public void withdraw(double amount) {
        System.out.println("[Proxy] Intento de retiro detectado. Verificando acceso...");
        // Lógica de control añadida por el proxy
        if (isUserAuthorized()) {
            System.out.println("[Proxy] Acceso autorizado. Delegando al objeto real.");
            realAccount.withdraw(amount); // Delega la llamada si la verificación es exitosa
        } else {
            System.out.println("[Proxy] Acceso denegado. El usuario no es el propietario.");
        }
    }

    @Override
    public double getBalance() {
        // También se podría añadir seguridad aquí si fuera necesario
        return realAccount.getBalance();
    }

    private boolean isUserAuthorized() {
        // Lógica de seguridad simple. En un caso real, esto sería más complejo.
        return "OWNER".equals(authenticatedUser);
    }
}
