/********************************************************************************
 * Copyright (c) 10/19/2022 Contributors to the Eclipse Foundation
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0, or the Eclipse Distribution License
 * v1.0 which is available at
 * https://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: EPL-2.0 OR BSD-3-Clause
 ********************************************************************************/
package tpi135_2023.ingenieria.occ.ues.edu.sv.Delivery;


import jakarta.annotation.sql.DataSourceDefinition;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationPath("")
@ApplicationScoped
@DataSourceDefinition(
        name = "java:global/deliveryDS",
        className = "org.postgresql.ds.PGSimpleDataSource",
        user = "postgres",        
        password = "abc123",
        url = "jdbc:postgresql://db:5432/delivery",
        properties = {
            "allowPublicKeyRetrieval=true",
            "useSSL=false",
            "requireSSL=false"
        }
)
public class ApplicationConfig extends Application {
    
}
