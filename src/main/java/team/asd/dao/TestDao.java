package team.asd.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import team.asd.mapper.TestMapper;

@Repository
public class TestDao {

	@Autowired
	private TestMapper testMapper;

	public void insertValue(String value) {
		testMapper.insertValue(value);
	}

}
