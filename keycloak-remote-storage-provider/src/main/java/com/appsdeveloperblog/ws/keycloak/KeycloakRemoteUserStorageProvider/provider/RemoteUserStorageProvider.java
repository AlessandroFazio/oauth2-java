package com.appsdeveloperblog.ws.keycloak.KeycloakRemoteUserStorageProvider.provider;

import com.appsdeveloperblog.ws.keycloak.KeycloakRemoteUserStorageProvider.response.User;
import com.appsdeveloperblog.ws.keycloak.KeycloakRemoteUserStorageProvider.response.VerifyPasswordResponse;
import com.appsdeveloperblog.ws.keycloak.KeycloakRemoteUserStorageProvider.service.UsersApiService;
import org.keycloak.component.ComponentModel;
import org.keycloak.credential.CredentialInput;
import org.keycloak.credential.CredentialInputValidator;
import org.keycloak.credential.UserCredentialStore;
import org.keycloak.models.*;
import org.keycloak.models.credential.PasswordCredentialModel;
import org.keycloak.storage.UserStorageProvider;
import org.keycloak.storage.adapter.AbstractUserAdapter;
import org.keycloak.storage.user.UserLookupProvider;

public class RemoteUserStorageProvider
        implements UserStorageProvider, UserLookupProvider, CredentialInputValidator {
    private KeycloakSession session;
    private ComponentModel model;
    private UsersApiService usersApiService;

    public RemoteUserStorageProvider(
            KeycloakSession session,
            ComponentModel model,
            UsersApiService usersApiService
    ) {
        this.session = session;
        this.model = model;
        this.usersApiService = usersApiService;
    }

    @Override
    public void close() {

    }

    @Override
    public UserModel getUserById(RealmModel realm, String id) {
        return UserLookupProvider.super.getUserById(realm, id);
    }

    @Override
    public UserModel getUserById(String s, RealmModel realmModel) {
        return null;
    }

    @Override
    public UserModel getUserByUsername(RealmModel realm, String username) {
        return UserLookupProvider.super.getUserByUsername(realm, username);
    }

    @Override
    public CredentialValidationOutput getUserByCredential(RealmModel realm, CredentialInput input) {
        return UserLookupProvider.super.getUserByCredential(realm, input);
    }

    @Override
    public UserModel getUserByUsername(String username, RealmModel realmModel) {

        User user = usersApiService.getUserDetails(username);

        if(user == null) return null;

        return createUserModel(username, realmModel);
    }

    private UserModel createUserModel(String username, RealmModel realm) {
        return new AbstractUserAdapter(session, realm, model) {
            @Override
            public String getUsername() {
                return username;
            }

            @Override
            public SubjectCredentialManager credentialManager() {
                return null;
            }
        };
    }

    @Override
    public UserModel getUserByEmail(RealmModel realm, String email) {
        return UserLookupProvider.super.getUserByEmail(realm, email);
    }

    @Override
    public UserModel getUserByEmail(String s, RealmModel realmModel) {
        return null;
    }

    @Override
    public boolean supportsCredentialType(String credentialType) {
        return PasswordCredentialModel.TYPE.equals(credentialType);
    }

    @Override
    public boolean isConfiguredFor(RealmModel realmModel, UserModel userModel, String credentialType) {
        if(!supportsCredentialType(credentialType)) return false;

        return !getCredentialStore().getStoredCredentialsByType(realmModel, userModel, credentialType).isEmpty();
    }

    private UserCredentialStore getCredentialStore() {
        return session.userCredentialManager();
    }

    @Override
    public boolean isValid(RealmModel realmModel, UserModel userModel, CredentialInput credentialInput) {
        VerifyPasswordResponse response = usersApiService.verifyUserPassword(
                userModel.getUsername(), credentialInput.getChallengeResponse());

        if(response == null) return false;

        return response.isResult();
    }
}
