# OTP-for-mail
register,verify,regenerate otp, login
## Project Structure

### Entities
- **Users**: Represents the user entity with attributes such as first name, last name, email, mobile number, OTP, and OTP generation time.

### DTOs
- **RegisterDto**: Data Transfer Object used for registering a new user.
- **LoginDto**: Data Transfer Object used for logging in a user with email and OTP.

### Controllers
- **UserController**: Provides REST API endpoints for user registration, account verification, OTP regeneration, and login.

### Configuration
- **EmailConfig**: Configures the JavaMailSender to send emails via SMTP.

### Utilities
- **EmailUtil**: Utility class to send OTP emails.
- **OtpUtil**: Utility class to generate OTPs.

### Services
- **UserService**: Contains business logic for user registration, account verification, OTP regeneration, and login.

## Endpoints

### Register User
- **Endpoint**: `POST /api/v1/user/register`
- **Description**: Registers a new user and sends an OTP to their email.
- **Request Body**:
  ```json
  {
    "firstName": "Sam",
    "lastName": "vimal",
    "email": "sam@gmail.com",
    "mobileNumber": "0762349076"
  }

###  Verify Account
Endpoint: PUT /api/v1/user/verifyaccount
Description: Verifies the user's account using the OTP sent to their email.
Request Parameters:
email: The email of the user.
otp: The OTP received by the user.

### Regenerate OTP
Endpoint: PUT /api/v1/user/regenerateOtp
Description: Regenerates a new OTP and sends it to the user's email.
Request Parameters:
email: The email of the user.


### Login
Endpoint: PUT /api/v1/user/login
Description: Logs in the user using their email and OTP.
