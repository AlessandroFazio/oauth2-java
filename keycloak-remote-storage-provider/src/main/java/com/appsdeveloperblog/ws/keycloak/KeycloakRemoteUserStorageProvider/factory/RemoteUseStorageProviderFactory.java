package com.appsdeveloperblog.ws.keycloak.KeycloakRemoteUserStorageProvider.factory;

import com.appsdeveloperblog.ws.keycloak.KeycloakRemoteUserStorageProvider.provider.RemoteUserStorageProvider;
import com.appsdeveloperblog.ws.keycloak.KeycloakRemoteUserStorageProvider.service.UsersApiService;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.jboss.resteasy.client.jaxrs.internal.ResteasyClientBuilderImpl;
import org.keycloak.common.util.Resteasy;
import org.keycloak.component.ComponentModel;
import org.keycloak.models.KeycloakSession;
import org.keycloak.storage.UserStorageProviderFactory;
import org.springframework.beans.factory.annotation.Value;

public class RemoteUseStorageProviderFactory implements UserStorageProviderFactory<RemoteUserStorageProvider> {
    public static final String PROVIDER_NAME = "keycloak-remote-psql-user-storage-provider";

    @Value("${keycloakRemoteStorageProvider.api.usersService.serviceUrl}")
    private static String USERS_API_SERVICE_URI;

    @Override
    public RemoteUserStorageProvider create(KeycloakSession session, ComponentModel model) {
        return new RemoteUserStorageProvider(session, model, buildHttpClient(USERS_API_SERVICE_URI));
    }

    @Override
    public String getId() {
        return PROVIDER_NAME;
    }

    private UsersApiService buildHttpClient(String uri) {

        ResteasyClient client = new ResteasyClientBuilderImpl().build();
        ResteasyWebTarget target = client.target(uri);

        return target.proxyBuilder(UsersApiService.class)
                .classloader(UsersApiService.class.getClassLoader())
                .build();
    }
}
