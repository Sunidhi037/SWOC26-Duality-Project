class Stack {
    int max = 5;
    int top = -1;
    int stack[] = new int[max];

void push(int item) {
if (top == max - 1) {
System.out.println(&quot;Stack Overflow&quot;);
} else {
stack[++top] = item;
System.out.println(item + &quot; pushed into stack&quot;);

}
}

void pop() {
if (top == -1) {
System.out.println(&quot;Stack Underflow&quot;);
} else {
System.out.println(&quot;Popped element: &quot; + stack[top--]);
}
}

void display() {
if (top == -1) {
System.out.println(&quot;Stack is empty&quot;);
} else {
System.out.println(&quot;Stack elements:&quot;);
for (int i = top; i &gt;= 0; i--) {
System.out.println(stack[i]);
}
}
}
}

public class StackDemo {
public static void main(String[] args) {
Scanner sc = new Scanner(System.in);
Stack s = new Stack();
int choice, value;

do {
System.out.println(&quot;\n1.Push 2.Pop 3.Display 4.Exit&quot;);
System.out.print(&quot;Enter choice: &quot;);

choice = sc.nextInt();

switch (choice) {
case 1:
System.out.print(&quot;Enter value: &quot;);
value = sc.nextInt();
s.push(value);
break;
case 2:
s.pop();
break;
case 3:
s.display();
break;
}
} while (choice != 4);
}
}