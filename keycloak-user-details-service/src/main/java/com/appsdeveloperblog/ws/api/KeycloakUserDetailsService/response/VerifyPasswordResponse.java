package com.appsdeveloperblog.ws.api.KeycloakUserDetailsService.response;

public class VerifyPasswordResponse {
    private boolean result;

    public boolean isResult() {
        return result;
    }

    private VerifyPasswordResponse(VerifyPasswordResponseBuilder builder) {
        this.result = builder.result;
    }

    public static class VerifyPasswordResponseBuilder {
        private boolean result;

        public VerifyPasswordResponseBuilder() {
        }

        public VerifyPasswordResponseBuilder setResult(boolean result) {
            this.result = result;
            return this;
        }

        public VerifyPasswordResponse build() {
            return new VerifyPasswordResponse(this);
        }
    }
}
