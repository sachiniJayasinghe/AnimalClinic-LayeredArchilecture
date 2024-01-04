package lk.ijse.animal_clinic.dao.custom.impl;

import lk.ijse.animal_clinic.dao.SQLUtil;
import lk.ijse.animal_clinic.dao.custom.EmployeeDAO;
import lk.ijse.animal_clinic.dto.EmployeeDto;
import lk.ijse.animal_clinic.dto.customerDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeDAOImpl implements EmployeeDAO {
    @Override
    public ArrayList<EmployeeDto> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM employee");
        ArrayList<EmployeeDto> allEmployee= new ArrayList<>();

        while (rst.next()) {
            EmployeeDto entity = new EmployeeDto(
                    rst.getString("id"),
                    rst.getString("name"),
                    rst.getString("tel"),
                    rst.getString("e_mail"),
                    rst.getString("address"));
            allEmployee.add(entity);
        }
        return allEmployee;
    }

    @Override
    public boolean save(EmployeeDto dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.
                execute("INSERT INTO employee VALUES(?, ?, ?, ?,?)",
                        dto.getEmp_id(), dto.getName(), dto.getTel(), dto.getEmail(), dto.getAddress());
    }

    @Override
    public boolean update(EmployeeDto dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE employee  SET  emp_name = ? , tel = ? , e_mail=? , address = ?  WHERE emp_id =?",
                dto.getEmp_id(), dto.getName(), dto.getTel(), dto.getEmail(), dto.getAddress());
    }

        @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
            ResultSet rst = SQLUtil.execute("SELECT id FROM employee WHERE id=?", id);
            return rst.next();
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM employee WHERE id=?", id);

    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT id FROM employee ORDER BY id DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("id");
            int newEmployeeId = Integer.parseInt(id.replace("E0-", "")) + 1;
            return String.format("E0-%03d", newEmployeeId);
        } else {
            return "E001";
        }
    }

    @Override
    public EmployeeDto search(String id) throws SQLException, ClassNotFoundException {

        ResultSet rst = SQLUtil.execute("SELECT * FROM employee WHERE emp_id = ?");
        rst.next();
        return new EmployeeDto(id + "", rst.getString("name"), rst.getString("tel"), rst.getString("e_mail"), rst.getString("address"));
    }

}
