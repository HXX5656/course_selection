package main.service;

import com.google.gson.JsonObject;
import main.DAO.AccountDAO;
import main.DAO.DAOFactory;
import main.util.EncryptUtil;

public class LoginServiceImpl implements LoginService {
    private JsonObject jsonObject;
    private AccountDAO accountDAO;
    public LoginServiceImpl(JsonObject param) {
        this.jsonObject=param;
        this.accountDAO= DAOFactory.getAccountDAOInstance();
    }
    @Override
    public JsonObject login() {
        JsonObject ret = new JsonObject();
        String usrname=jsonObject.get("account").getAsString();
        String pwd_input=jsonObject.get("password").getAsString();
        String pwd=accountDAO.infoList(usrname);
        if(pwd == null) {
            ret.addProperty("result","没有这个账户，请重新输入");
            ret.addProperty("success",false);
        }else {
            String result;
            if(EncryptUtil.verify(usrname,pwd_input,pwd)){
                result = "您已成功登录";
                ret.addProperty("success",true);
            } else {
                result = "密码错误，请重新输入";
                ret.addProperty("success",false);
            }
            ret.addProperty("result",result);
        }
        return ret;

    }
}
