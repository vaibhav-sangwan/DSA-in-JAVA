// import java.lang.Object;
class Complex {
    private int real, imaginary;

    public Complex(int real, int imaginary)
    {
        this.real = real;
        this.imaginary = imaginary;
    }

    public void print()
    {
        System.out.println(real + " + i" + imaginary);
    }

    public void add(Complex c2)
    {
        this.real += c2.real;
        this.imaginary += c2.imaginary;
    }

    public void multiply(Complex c2)
    {
        int temp_real = (this.real * c2.real) - (this.imaginary * c2.imaginary);
        int temp_imag = (this.real * c2.imaginary) + (this.imaginary * c2.real);
        this.real = temp_real;
        this.imaginary = temp_imag;
    }

}

class Polynomial {
    private int[] arr;
    private int degree = -1;

    public int degree(){
        return degree;
    }

    public Polynomial()
    {
        arr = new int[5];
    }

    private Polynomial(int size)
    {
        arr = new int[size];
    }

    public void set_coefficient(int coeff, int i)
    {
        while(i >= arr.length)
        {
            int[] temp = arr;
            arr = new int[arr.length * 2];
            for(int j = 0; j < temp.length; j++)
                arr[j] = temp[j];
        }
        if(i > degree)
            degree = i;
        arr[i] = coeff;
    }

    static public Polynomial add(Polynomial p1, Polynomial p2)
    {
        int p3_size = Math.max(p1.degree(), p2.degree()) + 1;
        Polynomial p3 = new Polynomial(p3_size);
        for(int i = 0; i < p3_size; i++)
        {
            p3.set_coefficient(p1.get_coefficient(i) + p2.get_coefficient(i), i);
        }
        return p3;
    }

    static public Polynomial sub(Polynomial p1, Polynomial p2)
    {
        int p3_size = Math.max(p1.degree(), p2.degree()) + 1;
        Polynomial p3 = new Polynomial(p3_size);
        for(int i = 0; i < p3_size; i++)
            p3.set_coefficient(p1.get_coefficient(i) - p2.get_coefficient(i), i);
        return p3;
    }

    static public Polynomial multiply(Polynomial p1, Polynomial p2)
    {
        int p3_size = p1.degree() + p2.degree() + 1;
        Polynomial p3 = new Polynomial(p3_size);
        for(int i = 0; i <= p1.degree(); i++)
        {
            for(int j = 0; j <= p2.degree(); j++)
                p3.set_coefficient(p3.get_coefficient(i + j) + (p1.get_coefficient(i) * p2.get_coefficient(j)), i + j);
        }
        return p3;
    }

    public int get_coefficient(int i)
    {
        if(i >= arr.length || i < 0)
            return 0;
        return arr[i];
    }

    public void print()
    {
        for(int i = 0; i < arr.length; i++)
        {
            if(arr[i] != 0)
                System.out.print(arr[i] + "x" + i + " ");
        }
        System.out.println();
    }

}

class Person {
    int age;
    String name;
    private int salary;

    void set_salary(int salary)
    {
        this.salary = salary;
    }

    int get_salary()
    {
        return salary;
    }

    void print()
    {
        System.out.println("RAN FROM BASE CLASS");
    }
}

class Student extends Person {
    int rollnum;
    Student()
    {
        set_salary(10000);
    }

    void print_salary()
    {
        System.out.println(get_salary());
    }

    void print()
    {
        System.out.println("RAN FROM DERIVED CLASS");
    }

}

class DivideByZeroException extends Exception {

}

class oops {
    public static int divide(int a , int b) throws IndexOutOfBoundsException, NullPointerException
    {
        try{
            if(b == 0)
                throw new DivideByZeroException();
            return a/b;
        } catch(DivideByZeroException e) {
            return a;
        } catch(IndexOutOfBoundsException e) {

        } catch(NullPointerException e){

        }
        return a/b;
        
        
    }
    public static void main(String[] args){
        try {
            System.out.println("hello");
            return;
        } catch(Exception e)
        {
            System.out.println("Error occured");
        }
        finally {
            System.out.println("Within finally block");
        }
        System.out.println("World");
    }
}