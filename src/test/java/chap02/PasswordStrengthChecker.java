package chap02;

public class PasswordStrengthChecker {

    public PasswordStrength checker(String s) {
        if (s == null || s.isEmpty()) return PasswordStrength.INVALID;
        int checkCounts = 0;

        if (s.length() >= 8) checkCounts++;
        if (CheckContainingNumberCriteria(s)) checkCounts++;
        if (CheckContainingUppercaseCriteria(s)) checkCounts++;

        if (checkCounts == 1) return PasswordStrength.WEAK;
        if (checkCounts == 2) return PasswordStrength.NORMAL;

        return PasswordStrength.STRONG;
    }

    private boolean CheckContainingUppercaseCriteria(String s) {
        for (char ch : s.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                return true;
            }
        }
        return false;
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
