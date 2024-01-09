package lk.ijse.animal_clinic.dao.custom.impl;

import lk.ijse.animal_clinic.dao.SQLUtil;
import lk.ijse.animal_clinic.dao.custom.SalaryDAO;
import lk.ijse.animal_clinic.dto.PetDto;
import lk.ijse.animal_clinic.dto.SalaryDto;
import lk.ijse.animal_clinic.dto.customerDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SalaryDAOImpl implements SalaryDAO {
    @Override
    public ArrayList<SalaryDto> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM salary");
        ArrayList<SalaryDto> allSalary= new ArrayList<>();

        while (rst.next()) {
            SalaryDto entity = new SalaryDto(
                    rst.getString("salary_id"),
                    rst.getString("emp_id"),
                    rst.getDate("date"),
                    rst.getString("salary_month"),
                    rst.getDouble("amount"));
            allSalary.add(entity);
        }
        return allSalary;
    }

    @Override
    public boolean save(SalaryDto dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.
                execute("INSERT INTO salary VALUES(?, ?, ?, ?,?)",
                        dto.getSalary_id(), dto.getEmp_id(), dto.getDate(), dto.getSalary_month(), dto.getAmount());
    }


    @Override
    public boolean update(SalaryDto dto) throws SQLException, ClassNotFoundException {

        return SQLUtil.
                execute("UPDATE salary SET  emp_id = ?  , date=? , salary_month= ? , amount=? WHERE salary_id =?",
                        dto.getEmp_id(), dto.getDate(), dto.getSalary_month(), dto.getAmount(), dto.getSalary_id());
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT salary_id FROM salary WHERE salary_id=?", id);
        return rst.next();
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM salary WHERE salary_id=?", id);

    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT salary_id FROM salary  ORDER BY salary_id DESC LIMIT 1");
        if (rst.next()) {
            String id = rst.getString("salary_id");
            int newCustomerId = Integer.parseInt(id.replace("S00", "")) + 1;
            return String.format("S%03d", newCustomerId);
        } else {
            return "S001";
        }

}

    @Override
    public SalaryDto search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM salary WHERE salary_id = ?", id);
        rst.next();
        return new SalaryDto(id + "", rst.getString("emp_id"), rst.getDate("date"), rst.getString("salary_month"), rst.getDouble("amount"));

    }
}
