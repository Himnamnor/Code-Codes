package Recursive_Analysis_of_Nested_Class_Problems;

public class DecoderString {
    public static String decodeString(String str){
        where=0;
        return f(str.toCharArray(),0);
    }

    public static int where=0;

    public static String f(char[] s,int i){
        StringBuilder ans=new StringBuilder();
        int cnt=0;
        while (i<s.length&&s[i]!=']'){
            if((s[i]>='a'&&s[i]<='z')||(s[i]>='A'&&s[i]<='Z')){
                ans.append(s[i++]);
            }
            else if(s[i]>='0'&&s[i]<='9'){
                cnt=cnt*10+s[i++]-'0';
            }
            else{
                ans.append(get(cnt,f(s,i+1)));
                i=where+1;
                cnt=0;
            }
        }
        where=i;
        return ans.toString();
    }

    public static String get(int cnt,String str){
        StringBuilder ans=new StringBuilder();
        for(int i=0;i<cnt;i++){
            ans.append(str);
        }
        return ans.toString();
    }
}
