<div class="login container">
 
    <form class="login-standard needs-validation" novalidate>
        <h2 class="my-3">Hello there</h2>
        <p class="login-info mb-3">Sign In now for great values at <span>GovDeals</span>.</p>
        
        <div class="form-group">
            <input name="userName" id="userName" tabindex="0" required class="form-control height-auto" placeholder="Username">
            <div class="invalid-feedback">
                Please enter your username.
            </div>
        </div>
        
        <div class="form-group">
            <input tabindex="0" type="password" name="password" id="password" required autocomplete="off" class="form-control height-auto" placeholder="Password">
            <div class="invalid-feedback">
                Please enter your password.
            </div>
        </div>
        
        <div class="form-check mb-3">
            <input type="checkbox" id="keepLogin" name="staySignIn" class="form-check-input">
            <label for="keepLogin" class="form-check-label">Stay Signed In</label>
        </div>
        
        <div name="divLoginErrorMessage" ng-if="signIn.errorMessage" class="invalid-feedback d-block">
            <!-- Error message goes here -->
        </div>
        
        <button id="btnSignIn" type="submit" class="btn btn-block btn-primary">Sign In</button>
        <p class="mb-4">Forgot your <a id="btnForgotUsernamePopup" class="text-lowercase" href="/account/forgot-username">Username</a> or <a id="btnForgotPasswordPopup" class="text-lowercase" href="/account/forgot-password">Password</a>?</p>
        <h3 class="my-3">Don’t have an account?</h3>
        <a id="registerBtn" role="button" class="btn btn-sm btn-outline-primary d-block" href="/account/register">Register</a>
    </form>
    

</div>