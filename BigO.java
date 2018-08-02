public class BigO {

    public void foo(int[] array) {

        for (int i = 0; i < array.length; i++) {
            // O(n)
        }

        for (int i = 0; i < array.length; i++) {
            // O(n)
        }
    }

    public void bar(int[] array1, int[] array2) {

        for (int i = 0; i < array1.length; i++) {
            // O(array1.length)
            for (int j = 0; j < array2.length; j++) {
                // O(array2.length)
            }
        }
    }

    public void bar(int[] array1, int[] array2) {

        for (int i = 0; i < array1.length; i++) {
            // ...
            for (int j = 0; j < array2.length; j++) {
                // ...
                if (array[i] < array[j]) {
                    // Launch!
                    // same as the previous one
                }
            }
        }
    }

    public void beep(int[] array) {

        for (int i = 0; i < array.length; i++) {
            // ...
            for (int j = 0; j < array.length; j++) {
                // ...
                for (int k = 0; k < 9999999; k++) {
                    // Jump!
                }
            }
        }
    }



}