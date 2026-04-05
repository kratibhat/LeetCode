package codesnippet.interview;

import java.util.HashSet;
import java.util.Set;

class P
{
    String name;
    P(String name)
    {
        this.name=name;
    }
    @Override
    public int hashCode()
    {
        return name.hashCode();
    }
    @Override
    public boolean equals(Object obj)
    {
        if(this==obj)
            return true;
        if(obj==null || obj.getClass()!=this.getClass())
            return false;
        P p=(P)obj;
        return this.name.equals(p.name);
    }

    @Override
    public String toString()
    {
        return name;
    }

}
public class MutableHashSet {
    public static void main(String[] args) {
  Set<P> s=new HashSet<P>();
  P p=new P("a");
  s.add(new P("A"));
    s.add(new P("B"));
    System.out.println(s.contains(new P("B")));
    p.name="H";
    s.remove(p);
    System.out.println(s);
    }
}
