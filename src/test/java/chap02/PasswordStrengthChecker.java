package chap02;

public class PasswordStrengthChecker {

    public PasswordStrength checker(String s) {
        if (s.length() < 8) {
            return PasswordStrength.NORMAL;
        }
        boolean containsNum = CheckContainingNumberCriteria(s);
        if (!containsNum) return PasswordStrength.NORMAL;
        return PasswordStrength.STRONG;
    }

    private boolean CheckContainingNumberCriteria(String s) {
        for (char ch: s.toCharArray()) {
            if (ch >= '0' && ch <= '9') {
                return true;
            }
        }
        return false;
    }
}
