package com.example.middleware;

import java.util.List;

public class Routes {


    private List<String> adminRoutes;
    private List<String> clientRoutes;
    private List<String> supplierRoutes;


    private List<String> publicRoutes;

    public Routes(List<String> adminRoutes, List<String> clientRoutes, List<String> supplierRoutes, List<String> publicRoutes) {
        this.adminRoutes = adminRoutes;
        this.clientRoutes = clientRoutes;
        this.supplierRoutes = supplierRoutes;
        this.publicRoutes = publicRoutes;
    }

    public List<String> getAdminRoutes() {
        return adminRoutes;
    }

    public void setAdminRoutes(List<String> adminRoutes) {
        this.adminRoutes = adminRoutes;
    }

    public List<String> getClientRoutes() {
        return clientRoutes;
    }

    public void setClientRoutes(List<String> clientRoutes) {
        this.clientRoutes = clientRoutes;
    }

    public List<String> getSupplierRoutes() {
        return supplierRoutes;
    }

    public void setSupplierRoutes(List<String> supplierRoutes) {
        this.supplierRoutes = supplierRoutes;
    }

    public List<String> getPublicRoutes() {
        return publicRoutes;
    }

    public void setPublicRoutes(List<String> publicRoutes) {
        this.publicRoutes = publicRoutes;
    }



}
