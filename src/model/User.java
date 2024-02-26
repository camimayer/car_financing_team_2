package model;

public class User {
    private String fullName;
    private String email;
    private String password;
    private String phoneNumber;

    public User(String fullName, String email, String password, String phoneNumber){
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    public User() {
    }

    //  String password = "12345678";
    //    byte[] salt = getSalt();
    //
    //    byte[] hashedPassword = hashPasswordWithSalt(password.getBytes(), salt);
    //
    //    //verification
    //    byte[] verificationHash = hashPasswordWithSalt("12345678".getBytes(), salt);
    //
    //    System.out.println("Password verification successful: " + Arrays.equals(hashedPassword, verificationHash));
    //}
    //
    //private static byte[] hashPasswordWithSalt(byte[] password, byte[] salt) {
    //    try {
    //        MessageDigest md = MessageDigest.getInstance("SHA-256");
    //        md.update(salt);
    //
    //        return md.digest(password);
    //
    //    } catch (NoSuchAlgorithmException e) {
    //        throw new RuntimeException("Hashing failed");
    //    }
    //}
    //
    //private static byte[] getSalt() {
    //    SecureRandom sr = new SecureRandom();
    //    byte[] salt = new byte[16];
    //    sr.nextBytes(salt);
    //
    //    return salt;
    //}


    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    @Override
    public String toString() {
        return "User{" +
                "fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
