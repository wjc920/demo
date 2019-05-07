package wjc920.demo.java.base;

/**
 * 验证static的重写
 * 总结：
 * 在方法被重写的情况下：
 * 1.静态方法的调用取决于引用类型；
 * 2.实例方法的调用取决于对象类型；
 *
 * @author wjc
 * @date 2019年05月06日 下午04:23:24
 */
public class StaticTest {
    public static void main(String[] args) {
        System.out.println("通过类调用静态方法");
        Parent.print();//parent static print...
        Son.print();//son static print...
        System.out.println("通过对象调用静态方法");
        Parent parent=new Parent();
        Son son = new Son();
        Parent parent1 = new Son();
        parent.print();//parent static print...   引用为Parent类型，所以调用Parent的print方法
        son.print();//son instance print...   引用为Son类型，所以调用Son的print方法
        parent1.print();//parent static print...   引用为Parent类型，所以调用Parent的print方法
        System.out.println("通过对象调用实例方法");
        parent.instancePrint();//son instance print...   引用指向对象为Parent，所以调用Parent的instancePrint方法
        son.instancePrint();//son instance print...   引用指向对象为Son，所以调用Son的instancePrint方法
        parent1.instancePrint();//son instance print...   引用指向对象为Son，所以调用Son的instancePrint方法
    }
}
class Parent{
    public static void print(){
        System.out.println("parent static print...");
    }
    public void instancePrint(){
        System.out.println("parent instance print...");
    }
}
class Son extends Parent{
    public static void print(){
        System.out.println("son static print...");
    }
    public void instancePrint(){
        System.out.println("son instance print...");
    }
}
