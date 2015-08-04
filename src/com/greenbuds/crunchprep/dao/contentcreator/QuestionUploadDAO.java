package com.greenbuds.crunchprep.dao.contentcreator;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import com.greenbuds.crunchprep.bo.contentcreator.AnswersBO;
import com.greenbuds.crunchprep.bo.contentcreator.ChoicesBO;
import com.greenbuds.crunchprep.bo.contentcreator.QuestionLessonsBO;
import com.greenbuds.crunchprep.bo.contentcreator.QuestionsUploadBO;
import com.greenbuds.crunchprep.data.DatabaseConnection;
import com.greenbuds.crunchprep.exception.ConnectionException;
import com.greenbuds.crunchprep.exception.DBException;
import com.greenbuds.crunchprep.setups.Exceptions;
import com.greenbuds.crunchprep.util.DBUtil;

public class QuestionUploadDAO implements IQuestionUploadDAO {

	public Map<Integer, String> loadSections() throws Exception {
		// TODO Auto-generated method stub
		Map<Integer, String> map = new HashMap<Integer, String>();
		ResultSet resultSet = null;
		Connection connection = null;
		CallableStatement cs = null;
		Statement st = null;
		try {
			connection = DatabaseConnection.getCrunchPrepConnection();
			st = connection.createStatement();
			String query = "select * FROM cp_sections";
			resultSet = st.executeQuery(query);
			// map.put(0,"<Select>");
			while (resultSet.next()) {
				map.put(resultSet.getInt(1), resultSet.getString(2));
			}

		} catch (Exception ex) {
			throw ex;
		} finally {
			try {
				if (connection != null) {
					connection.close();

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return map;
	}

	public Map<Integer, String> loadDifficulties() throws Exception {
		// TODO Auto-generated method stub
		Map<Integer, String> map = new HashMap<Integer, String>();
		ResultSet resultSet = null;
		Connection connection = null;
		CallableStatement cs = null;
		Statement st = null;
		try {
			connection = DatabaseConnection.getCrunchPrepConnection();
			st = connection.createStatement();
			String query = "select * FROM cp_difficulty_level";
			resultSet = st.executeQuery(query);
			// map.put(0,"<Select>");
			while (resultSet.next()) {
				map.put(resultSet.getInt(1), resultSet.getString(2));
			}

		} catch (Exception ex) {
			throw ex;
		} finally {
			try {
				if (connection != null) {
					connection.close();

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return map;
	}

	public Map<Integer, String> loadSkills() throws Exception {
		// TODO Auto-generated method stub
		Map<Integer, String> map = new HashMap<Integer, String>();
		ResultSet resultSet = null;
		Connection connection = null;
		CallableStatement cs = null;
		Statement st = null;
		try {
			connection = DatabaseConnection.getCrunchPrepConnection();
			st = connection.createStatement();
			String query = "select * FROM cp_skill_level where section_id like 1";
			resultSet = st.executeQuery(query);
			// map.put(0,"<Select>");

			while (resultSet.next()) {
				map.put(resultSet.getInt(1), resultSet.getString(2));
			}

		} catch (Exception ex) {
			throw ex;
		} finally {
			try {
				if (connection != null) {
					connection.close();

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return map;
	}

	public List<QuestionsUploadBO> getQuestionsList(int section_id,
			int[] category_id, int[] type_id) throws Exception {
		// TODO Auto-generated method stub
		ResultSet resultSet = null;
		Connection connection = null;
		CallableStatement cs = null;
		Statement st = null;
		java.sql.PreparedStatement preparedStatement = null;
		List<QuestionsUploadBO> list = new ArrayList<QuestionsUploadBO>();
		try {
			connection = DatabaseConnection.getCrunchPrepConnection();
			// st = connection.createStatement();
			String sid = "";
			String catid = "IN(";
			String typeid = "IN(";
			if (section_id == -1)
				sid = sid + "%";
			else
				sid = sid + section_id;

			if (category_id.length == 1 && category_id[0] == -1) {

				catid = "like '%'";
			} else {
				for (int i = 0; i < category_id.length; i++) {
					if (i == 0) {

						catid = catid + category_id[i];

					} else
						catid = catid + "," + category_id[i];
				}
				catid = catid + ")";
			}
			/*
			 * if (category_id == -1) catid = catid + "%"; else catid = catid +
			 * category_id;
			 */
			if (type_id.length == 1 && type_id[0] == -1) {

				typeid = "like '%'";
			} else {
				for (int i = 0; i < type_id.length; i++) {
					if (i == 0) {

						typeid = typeid + type_id[i];

					} else
						typeid = typeid + "," + type_id[i];
				}
				typeid = typeid + ")";
			}
			/*
			 * if (type_id == -1) typeid = typeid + "%"; else typeid = typeid +
			 * type_id;
			 */
			System.out.println(typeid);
			String query = "SELECT t1.`question_id`,t2.question_no,t1.`test_id`,t1.`section_id`, t1.`category_id`, t1.`type_id`, t1.`question_title`, t1.`question_directions`, t8.`passage_type`, t8.`passage`, t2.question,t2.skill_id1,t2.skill_id2,t2.skill_id3,t2.diff_id,t2.avg_time, t2.quantityA,t2.quantityB,t2.solution_text,t2.solution_video,t3.section_name,t4.category_name,t5.lesson_name,t2.`access_type`,t6.test_name,t2.status,t7.question_type_name,t1.passage_id,t1.graph_id,t8.passage_title,t9.graph_title,t2.referral"
					+ " FROM `cp_question_masters` t1,cp_questions t2,cp_sections t3,cp_question_categories t4,cp_lessons t5,cp_testmaster t6,cp_question_type t7,cp_reading_comprehension_passage t8,cp_data_interpretation_graph t9 "
					+ " WHERE t1.question_id=t2.question_id AND t1.question_id LIKE '%' AND t2.question_no LIKE '%' AND t1.section_id=t3.section_id AND t1.test_id=t6.test_id AND t1.category_id=t4.category_id AND t1.section_id LIKE '"
					+ sid
					+ "' AND t1.category_id "
					+ catid
					+ " AND t1.type_id "
					+ typeid
					+ " AND t7.question_type_id=t1.type_id "
					+ " GROUP BY t1.`question_id`,t2.question_no "
					+ " ORDER BY t1.question_id,t2.question_no";
			preparedStatement = connection.prepareStatement(query);
			// System.out.println(preparedStatement.toString());
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				QuestionsUploadBO bo = new QuestionsUploadBO();
				bo.setQuestion_id(resultSet.getInt("question_id"));
				bo.setQuestion_no(resultSet.getInt("question_no"));
				bo.setTest_id(resultSet.getInt("test_id"));
				bo.setSection_id(resultSet.getInt("section_id"));
				bo.setCategory_id(resultSet.getInt("category_id"));
				bo.setQuestiontype_id(resultSet.getInt("type_id"));
				// bo.setLesson_id(resultSet.getInt("lession_id"));
				bo.setQuestion_title(resultSet.getString("question_title"));
				bo.setQuestion_directions(resultSet
						.getString("question_directions"));
				bo.setPassage_type(resultSet.getString("passage_type"));
				bo.setPassage(resultSet.getString("passage"));
				bo.setQuestion(resultSet.getString("question"));
				bo.setSkillid1(resultSet.getInt("skill_id1"));
				bo.setSkillid2(resultSet.getInt("skill_id2"));
				bo.setSkillid3(resultSet.getInt("skill_id3"));
				bo.setDifficulty_id(resultSet.getInt("diff_id"));
				bo.setAvg_time(resultSet.getString("avg_time"));
				bo.setQuantityA(resultSet.getString("quantityA"));
				bo.setQuantityB(resultSet.getString("quantityB"));
				// bo.setQuestion_image(resultSet.getString("question_image"));
				bo.setSolution_text(resultSet.getString("solution_text"));
				bo.setSolution_video(resultSet.getString("solution_video"));
				bo.setSection_name(resultSet.getString("section_name"));
				bo.setCategory_name(resultSet.getString("category_name"));
				bo.setLession_name(resultSet.getString("lesson_name"));
				bo.setAccess_type(resultSet.getString("access_type"));
				bo.setTest_name(resultSet.getString("test_name"));
				bo.setStatus(resultSet.getString("status"));
				bo.setQuestion_type(resultSet.getString("question_type_name"));
				bo.setPassageId(resultSet.getInt("passage_id"));
				bo.setGraphId(resultSet.getInt("graph_id"));
				// bo.setPassageTitle(resultSet.getString("passage_title"));
				// bo.setGraphTitle(resultSet.getString("graph_title"));
				bo.setRefferal(resultSet.getString("referral"));
				list.add(bo);
			}

		}

		catch (Exception ex) {
			throw ex;
		} finally {
			try {
				if (connection != null) {
					connection.close();

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return list;
	}

	public boolean saveQuestions(List<QuestionsUploadBO> bo)
			throws DBException, ConnectionException {
		// TODO Auto-generated method stub
		ResultSet resultSet = null;
		Connection connection = null;
		CallableStatement cs = null;
		Statement st = null;
		java.sql.PreparedStatement preparedStatement1 = null;
		java.sql.PreparedStatement preparedStatement2 = null;
		java.sql.PreparedStatement preparedStatement3 = null;
		java.sql.PreparedStatement preparedStatement4 = null;
		java.sql.PreparedStatement preparedStatement5 = null;
		boolean flag = false;
		try {
			connection = DatabaseConnection.getCrunchPrepConnection();
			try {
				connection.setAutoCommit(false);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new DBException(Exceptions.CONNECTION_COMMIT_FAILED);
			}
			QuestionsUploadBO questionsBO = bo.get(0);

			// System.out.println("Question ID\t"+questionsBO.getQuestion_id());
			

			String query0 = "INSERT INTO `cp_question_masters` (`question_id`,`section_id`, `category_id`, `type_id`, `test_id`, `passage_id`, `graph_id`,`question_title`, `question_directions`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
			preparedStatement1 = connection.prepareStatement(query0);
			String query1 = "INSERT INTO `cp_questions` (`question_id`, `skill_id1`, `skill_id2`, `skill_id3`, `diff_id`, `question_no`, `question`, `avg_time`, `quantityA`, `quantityB`, `solution_text`, `solution_video`,`access_type`,`referral`,`status`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

			preparedStatement2 = connection.prepareStatement(query1);
			String query2 = "INSERT INTO `cp_question_choices` (`question_id`,`question_no`,`choice_no` , `choice`,`choice_tooltip`) VALUES ( ?, ?, ? ,?, ?)";
			preparedStatement3 = connection.prepareStatement(query2);
			String query3 = "INSERT INTO `cp_question_answers` (`question_id`,`question_no`,`answer_no`, `answer`) VALUES ( ?, ?, ? ,?)";
			preparedStatement4 = connection.prepareStatement(query3);
			String query4 = "INSERT INTO `cp_question_lessons` (`question_id`,`sublesson_id`,`lesson_no`,`question_no`,`master_lesson`) VALUES ( ?, ?, ?, ?, ?)";
			preparedStatement5 = connection.prepareStatement(query4);

			int qid1 = questionsBO.getQuestion_id();

			if (questionsBO != null) {

				// System.out.println("qid1\t"+qid1);

				// preparedStatement1.setInt(1, qid1);

				preparedStatement1.setInt(1, qid1); // question_id
				preparedStatement1.setInt(2, questionsBO.getSection_id()); // section_id
				preparedStatement1.setInt(3, questionsBO.getCategory_id()); // category_id
				preparedStatement1.setInt(4, questionsBO.getQuestiontype_id()); // type_id
				preparedStatement1.setInt(5, questionsBO.getTest_id());
				preparedStatement1.setString(
						6,
						questionsBO.getPassageId() == 0 ? null : String
								.valueOf(questionsBO.getPassageId()));
				preparedStatement1.setString(
						7,
						questionsBO.getGraphId() == 0 ? null : String
								.valueOf(questionsBO.getGraphId()));
				preparedStatement1
						.setString(8, questionsBO.getQuestion_title());
				preparedStatement1.setString(9,
						questionsBO.getQuestion_directions());
				// System.out.println(preparedStatement1.toString());

				preparedStatement1.addBatch();

			}

			int i1 = 1;

			for (QuestionsUploadBO bo2 : bo) {

				System.out.println(bo2.getSkills());

				preparedStatement2.setInt(1, qid1); // we have to put id
				if (bo2.getSkills().size() == 1) {
					preparedStatement2.setString(2, bo2.getSkills().get(0)
							.toString());
					preparedStatement2.setString(3, null);
					preparedStatement2.setString(4, null);

				}

				if (bo2.getSkills().size() == 2) {
					preparedStatement2.setString(2, bo2.getSkills().get(0)
							.toString());
					preparedStatement2.setString(3, bo2.getSkills().get(1)
							.toString());
					preparedStatement2.setString(4, null);

				}

				if (bo2.getSkills().size() == 3) {
					preparedStatement2.setString(2, bo2.getSkills().get(0)
							.toString());
					preparedStatement2.setString(3, bo2.getSkills().get(1)
							.toString());
					preparedStatement2.setString(4, bo2.getSkills().get(2)
							.toString());

				}

				preparedStatement2.setInt(5, bo2.getDifficulty_id());
				preparedStatement2.setInt(6, i1); // we have to put no
				preparedStatement2.setString(7, bo2.getQuestion());
				preparedStatement2.setString(8, bo2.getAvg_time());
				preparedStatement2.setString(9, bo2.getQuantityA());
				preparedStatement2.setString(10, bo2.getQuantityB());
				preparedStatement2.setString(11, bo2.getSolution_text());
				preparedStatement2.setString(12, bo2.getSolution_video());
				preparedStatement2.setString(13, bo2.getAccess_type());
				preparedStatement2.setString(14,
						bo2.getRefferal() == null ? "NO" : bo2.getRefferal());

				preparedStatement2.setString(15,
						bo2.getStatus() == null ? "ACTIVE" : bo2.getStatus());
				// System.out.println(preparedStatement2.toString());
				preparedStatement2.addBatch();

				int j1 = 1; // for choice no
				int k1 = 1; // for answer no
				int p1 = 1; // for lesson no

				// insert related lessons for question
				if (bo2.getLessons() != null) {
					int index = 0;
					for (String lesson : bo2.getLessons()) {

						// System.out.println("Answers Insert\t" + answers);
						if (!lesson.isEmpty()) {
							if (index == 0) {
								preparedStatement5.setInt(1, qid1); // questionid
								preparedStatement5.setInt(2,
										Integer.parseInt(lesson));
								preparedStatement5.setInt(3, p1); // lesson no
								preparedStatement5.setInt(4, i1); // question no
								preparedStatement5.setString(5, "YES"); // question
																		// no

								System.out.println(preparedStatement5
										.toString());
								preparedStatement5.addBatch();
								index++;
								p1++;
							} else {
								preparedStatement5.setInt(1, qid1); // questionid
								preparedStatement5.setInt(2,
										Integer.parseInt(lesson));
								preparedStatement5.setInt(3, p1); // lesson no
								preparedStatement5.setInt(4, i1); // question no
								preparedStatement5.setString(5, null); // question
																		// no

								System.out.println(preparedStatement5
										.toString());
								preparedStatement5.addBatch();
								index++;
								p1++;
							}

						}

					}
				}

				// insert choices for question

				if (bo2.getChoices() != null) {
					for (String choices : bo2.getChoices()) {

						// System.out.println("Choices Insert\t" + choices);
						if (!choices.isEmpty()) {
							preparedStatement3.setInt(1, qid1);
							preparedStatement3.setInt(2, i1);
							preparedStatement3.setInt(3, j1);
							preparedStatement3.setString(4, choices);
							if (bo2.getTooltips() != null) {
								if (!bo2.getTooltips().isEmpty())
									preparedStatement3.setString(5, bo2
											.getTooltips().get(j1 - 1));
								else
									preparedStatement3.setString(5, null);
							} else
								preparedStatement3.setString(5, null);
							preparedStatement3.addBatch();
							j1++;
						}
					}
				}

				// insert answers for question
				if (bo2.getAnswers() != null) {
					for (String answers : bo2.getAnswers()) {

						// System.out.println("Answers Insert\t" + answers);
						if (!answers.isEmpty()) {
							preparedStatement4.setInt(1, qid1);
							preparedStatement4.setInt(2, i1);
							preparedStatement4.setInt(3, k1);
							preparedStatement4.setString(4, answers);
							preparedStatement4.addBatch();
							k1++;
						}

					}
				}
				i1++;
				// qid2++;
			}

			int i[] = preparedStatement1.executeBatch();
			int j[] = preparedStatement2.executeBatch();
			int k[] = preparedStatement3.executeBatch();
			int l[] = preparedStatement4.executeBatch();
			int m[] = preparedStatement5.executeBatch();

			if (i.length > 0 && j.length >= 0 && k.length >= 0 && l.length >= 0
					&& m.length >= 0) {
				// System.out.println("Success");
				connection.commit();
				flag = true;
			} else {
				// System.out.println("Failure");
			}

		}

		catch (SQLException ex) {
			// connection.rollback();
			throw new DBException(Exceptions.SQL_EXCEPTION, ex);
		} finally {
			try {
				if (connection != null) {
					connection.close();

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return flag;
	}

	public boolean updateQuestions(List<QuestionsUploadBO> bo) throws Exception {
		// TODO Auto-generated method stub
		ResultSet resultSet = null;
		Connection connection = null;
		CallableStatement cs = null;
		Statement st = null;
		java.sql.PreparedStatement preparedStatement1 = null;
		java.sql.PreparedStatement preparedStatement2 = null;
		java.sql.PreparedStatement preparedStatement3 = null;
		java.sql.PreparedStatement preparedStatement4 = null;
		java.sql.PreparedStatement preparedStatement5 = null;
		java.sql.PreparedStatement preparedStatement6 = null;
		boolean flag = false;
		try {
			connection = DatabaseConnection.getCrunchPrepConnection();
			// st = connection.createStatement();
			connection.setAutoCommit(false);
			int i1 = 1;
			// System.out.println("List Size (DAO)\t" + bo.size());
			QuestionsUploadBO questionsBO = bo.get(0);
			int qid11 = questionsBO.getQuestion_id();
			// System.out.println("Section is\t"+questionsBO.getSection_id());

			String query0 = "UPDATE `cp_question_masters` SET `section_id`=?, `category_id`=?, `type_id`=?, `test_id`=?, `passage_id`=?, `graph_id`=? ,`question_title`=?, `question_directions`=? WHERE  `question_id`= ?";
			preparedStatement1 = connection.prepareStatement(query0);

			String query1 = "UPDATE `cp_questions` SET  `skill_id1`=?, `skill_id2`=?, `skill_id3`=?, `diff_id`=?, `question`=?, `avg_time`=?, `quantityA`=?, `quantityB`=?, `solution_text`=?, `solution_video`=?, `access_type`=?, `referral`=?, `status`=?  WHERE `question_id`= ? and  `question_no`=? ";
			preparedStatement2 = connection.prepareStatement(query1);

			String query2 = "UPDATE `cp_question_choices` SET `choice`=? ,`choice_tooltip`=? WHERE  `question_id`=? and `question_no`= ? and `choice_no`=? ";
			preparedStatement3 = connection.prepareStatement(query2);

			String query3 = "UPDATE `cp_question_answers` SET `answer`=? WHERE  `question_id`=? and `question_no`=? and `answer_no`=? ";
			preparedStatement4 = connection.prepareStatement(query3);

			/*
			 * String query4 =
			 * "UPDATE `cp_question_lessons` SET `sublesson_id`=? WHERE  `question_id`=? AND `question_no`=? AND `lesson_no`=? "
			 * ; preparedStatement5 = connection.prepareStatement(query4);
			 */

			String query4 = "INSERT INTO `cp_question_lessons` (`question_id`,`sublesson_id`,`lesson_no`,`question_no`) VALUES ( ?, ?, ?, ?)";
			preparedStatement5 = connection.prepareStatement(query4);

			String query5 = "DELETE FROM `cp_question_lessons` WHERE  `question_id`=?";
			preparedStatement6 = connection.prepareStatement(query5);

			if (questionsBO != null) {
				preparedStatement1.setInt(1, questionsBO.getSection_id());
				preparedStatement1.setInt(2, questionsBO.getCategory_id());
				preparedStatement1.setInt(3, questionsBO.getQuestiontype_id());
				preparedStatement1.setInt(4, questionsBO.getTest_id());
				preparedStatement1.setString(
						5,
						questionsBO.getPassageId() == 0 ? null : String
								.valueOf(questionsBO.getPassageId()));
				preparedStatement1.setString(
						6,
						questionsBO.getGraphId() == 0 ? null : String
								.valueOf(questionsBO.getGraphId()));
				preparedStatement1
						.setString(7, questionsBO.getQuestion_title());
				preparedStatement1.setString(8,
						questionsBO.getQuestion_directions());
				preparedStatement1.setInt(9, qid11);
				// System.out.println(preparedStatement1.toString());
				preparedStatement1.addBatch();
			}

			for (QuestionsUploadBO bo2 : bo) {

				// System.out.println("Qid in DAO \t" + bo2.getQuestion_id());

				//
				// System.out.println("skills size\t"+bo2.getSkills().size());
				if (bo2.getSkills().size() == 1) {
					preparedStatement2
							.setInt(1,
									Integer.parseInt(bo2.getSkills().get(0)
											.toString()));
					preparedStatement2.setString(
							2,
							bo2.getSkillid2() == 0 ? null : String.valueOf(bo2
									.getSkillid2()));
					preparedStatement2.setString(
							3,
							bo2.getSkillid3() == 0 ? null : String.valueOf(bo2
									.getSkillid3()));
				}
				if (bo2.getSkills().size() == 2) {
					preparedStatement2
							.setInt(1,
									Integer.parseInt(bo2.getSkills().get(0)
											.toString()));
					preparedStatement2
							.setInt(2,
									Integer.parseInt(bo2.getSkills().get(1)
											.toString()));
					preparedStatement2.setString(
							3,
							bo2.getSkillid3() == 0 ? null : String.valueOf(bo2
									.getSkillid3()));
				}
				if (bo2.getSkills().size() == 3) {
					preparedStatement2
							.setInt(1,
									Integer.parseInt(bo2.getSkills().get(0)
											.toString()));
					preparedStatement2
							.setInt(2,
									Integer.parseInt(bo2.getSkills().get(1)
											.toString()));
					preparedStatement2
							.setInt(3,
									Integer.parseInt(bo2.getSkills().get(2)
											.toString()));
				}
				/*
				 * preparedStatement2.setInt(2, bo2.getSkillid1());
				 * preparedStatement2.setInt(3, bo2.getSkillid2());
				 * preparedStatement2.setInt(4, bo2.getSkillid3());
				 */
				preparedStatement2.setInt(4, bo2.getDifficulty_id());
				preparedStatement2.setString(5, bo2.getQuestion());
				preparedStatement2.setString(6, bo2.getAvg_time());
				preparedStatement2.setString(7, bo2.getQuantityA());
				preparedStatement2.setString(8, bo2.getQuantityB());

				preparedStatement2.setString(9, bo2.getSolution_text());
				preparedStatement2.setString(10, bo2.getSolution_video());
				preparedStatement2.setString(11, bo2.getAccess_type());
				preparedStatement2.setString(12, bo2.getRefferal());
				preparedStatement2.setString(13, bo2.getStatus());

				preparedStatement2.setInt(14, qid11);
				preparedStatement2.setInt(15, i1);
				// System.out.println(preparedStatement2.toString());
				preparedStatement2.addBatch();

				int j1 = 1; // for choices
				int k1 = 1; // for answers
				int p1 = 1; // for lessons

				
				// insert related lessons for question
				if (bo2.getLessons() != null) {
					preparedStatement6.setInt(1, bo2.getQuestion_id());
					preparedStatement6.addBatch();
					for (String lesson : bo2.getLessons()) {

						// System.out.println("Answers Insert\t" + answers);
						if (!lesson.isEmpty()) {
							preparedStatement5.setInt(1, bo2.getQuestion_id()); // questionid
							preparedStatement5.setInt(2,
									Integer.parseInt(lesson));
							preparedStatement5.setInt(3, p1); // lesson no
							preparedStatement5.setInt(4, i1); // question no

							preparedStatement5.addBatch();
							p1++;

						}

					}
				}

				// for choices
				if (bo2.getChoices() != null) {
					for (String choices : bo2.getChoices()) {

						if (!choices.isEmpty()) {
							preparedStatement3.setString(1, choices);
							if (bo2.getTooltips() != null) {
								if (!bo2.getTooltips().isEmpty())
									preparedStatement3.setString(2, bo2
											.getTooltips().get(j1 - 1));
								else
									preparedStatement3.setString(2, null);
							} else
								preparedStatement3.setString(2, null);
							preparedStatement3.setInt(3, bo2.getQuestion_id());
							preparedStatement3.setInt(4, i1);
							preparedStatement3.setInt(5, j1);
							// System.out.println(preparedStatement3.toString());
							preparedStatement3.addBatch();

							j1++;
						}
					}
				}

				// for answers
				if (bo2.getAnswers() != null) {
					for (String answers : bo2.getAnswers()) {

						if (!answers.isEmpty()) {
							preparedStatement4.setString(1, answers);
							preparedStatement4.setInt(2, bo2.getQuestion_id());
							preparedStatement4.setInt(3, i1);
							preparedStatement4.setInt(4, k1);
							// System.out.println(preparedStatement4.toString());
							preparedStatement4.addBatch();
							k1++;
						}

					}
				}
				i1++;
			}
			int i[] = preparedStatement1.executeBatch();
			int j[] = preparedStatement2.executeBatch();
			int k[] = preparedStatement3.executeBatch();
			int l[] = preparedStatement4.executeBatch();
			int n[] = preparedStatement6.executeBatch();
			int m[] = preparedStatement5.executeBatch();

			if (i.length >= 0 && j.length >= 0 && k.length >= 0
					&& l.length >= 0 && m.length >= 0 && n.length >= 0) {
				// System.out.println("Success");
				connection.commit();
				flag = true;
			} else {
				// System.out.println("Failure");
			}

		}

		catch (Exception ex) {
			throw ex;
		} finally {
			try {
				if (connection != null) {
					connection.close();

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return flag;

	}

	public boolean deleteQuestions(String[] s) throws Exception {
		// TODO Auto-generated method stub
		boolean flag = false;
		ResultSet resultSet = null;
		Connection connection = null;
		CallableStatement cs = null;
		Statement st = null;
		java.sql.PreparedStatement preparedStatement1 = null;

		try { 
			connection = DatabaseConnection.getCrunchPrepConnection();
			connection.setAutoCommit(false);
			String query = "DELETE FROM `cp_question_masters` WHERE  `question_id`=?";
			 System.out.println(query);
			preparedStatement1 = connection.prepareStatement(query);
			for (int k = 0; k < s.length; k++) {
				preparedStatement1.setInt(1, Integer.parseInt(s[k]));
				preparedStatement1.addBatch();
			}
			
			try{
				int i[] = preparedStatement1.executeBatch();
		    	if (i.length >= 0) {
				connection.commit();
				flag = true;

			}
			}
			catch(Exception e)
			{
				if(e.getMessage().contains("a foreign key constraint fails")){
					flag=false;
				}
				flag=false;
				//e.printStackTrace();
			}

		}

		catch (Exception ex) {
			flag=false; 
			throw ex;
			
		} finally {
			try {
				if (connection != null) {
					connection.close();

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		System.out.println("Flag Result\t"+flag);
		return flag;
	}

	public Map<Integer, String> loadTests(int section_id) throws Exception {
		// TODO Auto-generated method stub
		Map<Integer, String> map = new HashMap<Integer, String>();
		ResultSet resultSet = null;
		Connection connection = null;
		CallableStatement cs = null;
		Statement st = null;
		String sectionId = "";
		if (section_id == 1)
			sectionId = sectionId + "Practice Verbal";
		else if (section_id == 2)
			sectionId = sectionId + "Practice Math";
		else if (section_id == -1)
			sectionId = sectionId + "-1";
		else
			sectionId = sectionId + "%";
		try {
			connection = DatabaseConnection.getCrunchPrepConnection();
			st = connection.createStatement();
			String query = "select * FROM cp_testmaster t1";
			// System.out.println(query);
			resultSet = st.executeQuery(query);
			// map.put(0,"<Select>");
			while (resultSet.next()) {
				map.put(resultSet.getInt(1), resultSet.getString(2));
			}

		} catch (Exception ex) {
			throw ex;
		} finally {
			try {
				if (connection != null) {
					connection.close();

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return map;

	}

	public Map<Integer, String> loadLessons(int section_id, int category_id)
			throws Exception {
		// TODO Auto-generated method stub

		Map<Integer, String> map = new HashMap<Integer, String>();
		ResultSet resultSet = null;
		Connection connection = null;
		CallableStatement cs = null;
		Statement st = null;
		String sectionId = "";
		if (section_id == 0 || section_id == -1)
			sectionId = sectionId + "%";
		else
			sectionId = sectionId + section_id;

		String categoryId = "";
		if (category_id == 0 || category_id == -1)
			categoryId = categoryId + "%";
		else
			categoryId = categoryId + category_id;
		try {
			connection = DatabaseConnection.getCrunchPrepConnection();
			st = connection.createStatement();

			/*String query = "select * FROM cp_lessons t1,cp_lessons_masters t2 where t2.section_id like '"
					+ sectionId + "' and t1.lesson_id=t2.lesson_id";*/
			String query = "select * FROM cp_lessons t1,cp_lessons_masters t2 where  t1.lesson_id=t2.lesson_id";
			 System.out.println(query);

			resultSet = st.executeQuery(query);
			// map.put(0,"<Select>");
			while (resultSet.next()) {
				//map.put(resultSet.getInt(1), resultSet.getString(3));
				map.put(resultSet.getInt(1), resultSet.getString(3)+"("+resultSet.getString(15)+")");
			}

		} catch (Exception ex) {
			throw ex;
		} finally {
			try {
				if (connection != null) {
					connection.close();

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return map;

	}

	public List<ChoicesBO> getChoicesList() throws Exception {
		// TODO Auto-generated method stub
		ResultSet resultSet = null;
		Connection connection = null;
		CallableStatement cs = null;
		Statement st = null;
		java.sql.PreparedStatement preparedStatement = null;
		List<ChoicesBO> list = new ArrayList<ChoicesBO>();
		try {
			connection = DatabaseConnection.getCrunchPrepConnection();
			// st = connection.createStatement();

			String query = "SELECT  `question_id`,  `question_no`,  `choice`,  `choice_tooltip` FROM `cp_question_choices` ORDER BY  `question_id`,  `question_no` , `choice` ";
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				ChoicesBO bo = new ChoicesBO();
				bo.setQuestion_id(resultSet.getInt("question_id"));
				bo.setQuestion_no(resultSet.getInt("question_no"));
				bo.setChoice(resultSet.getString("choice"));
				bo.setTooltip(resultSet.getString("choice_tooltip"));
				list.add(bo);
			}
		}

		catch (Exception ex) {
			throw ex;
		} finally {
			try {
				if (connection != null) {
					connection.close();

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return list;
	}

	public List<AnswersBO> getAnswersList() throws Exception {
		// TODO Auto-generated method stub
		ResultSet resultSet = null;
		Connection connection = null;
		CallableStatement cs = null;
		Statement st = null;
		java.sql.PreparedStatement preparedStatement = null;
		List<AnswersBO> list = new ArrayList<AnswersBO>();
		try {
			connection = DatabaseConnection.getCrunchPrepConnection();
			// st = connection.createStatement();

			String query = "SELECT  `question_id`,  `question_no`,  `answer` FROM `cp_question_answers` ORDER BY  `question_id`,  `question_no` ,  `answer` ";
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				AnswersBO bo = new AnswersBO();
				bo.setQuestion_id(resultSet.getInt("question_id"));
				bo.setQuestion_no(resultSet.getInt("question_no"));
				bo.setAnswer(resultSet.getString("answer"));
				list.add(bo);
			}

		}

		catch (Exception ex) {
			throw ex;
		} finally {
			try {
				if (connection != null) {
					connection.close();

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gbbuds.crunchprep.dao.ContentCreDao.IQuestionUploadDAO#
	 * updateQuestionStatus(java.lang.String[], java.lang.String)
	 */
	@Override
	public boolean updateQuestionStatus(String[] s, String status,
			Map<String, String> map) throws Exception {
		// TODO Auto-generated method stub
		boolean flag = false;
		ResultSet resultSet = null;
		Connection connection = null;
		CallableStatement cs = null;
		Statement st = null;
		java.sql.PreparedStatement preparedStatement1 = null;

		try {

			connection = DatabaseConnection.getCrunchPrepConnection();
			connection.setAutoCommit(false);
			String query = "update `cp_questions` SET active='" + status
					+ "' WHERE  `question_id`=? and `question_no`=? ";
			// System.out.println(query);
			preparedStatement1 = connection.prepareStatement(query);
			for (int k = 0; k < s.length; k++) {
				preparedStatement1.setInt(1, Integer.parseInt(s[k]));
				preparedStatement1.setInt(2,
						Integer.parseInt(map.get(s[k]).toString()));
				// System.out.println(preparedStatement1.toString());
				preparedStatement1.addBatch();
			}
			int i[] = preparedStatement1.executeBatch();
			if (i.length >= 0) {
				connection.commit();
				flag = true;

			}

		}

		catch (Exception ex) {
			throw ex;
		} finally {
			try {
				if (connection != null) {
					connection.close();

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return flag;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gbbuds.crunchprep.dao.ContentCreDao.IQuestionUploadDAO#
	 * getQuestionLessonsList()
	 */
	@Override
	public List<QuestionLessonsBO> getQuestionLessonsList() throws Exception {
		// TODO Auto-generated method stub
		ResultSet resultSet = null;
		Connection connection = null;
		CallableStatement cs = null;
		Statement st = null;
		java.sql.PreparedStatement preparedStatement = null;
		List<QuestionLessonsBO> list = new ArrayList<QuestionLessonsBO>();
		try {
			connection = DatabaseConnection.getCrunchPrepConnection();
			// st = connection.createStatement();

			String query = "SELECT  `question_id`,  `question_no`,  `sublesson_id` FROM `cp_question_lessons`";
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				QuestionLessonsBO bo = new QuestionLessonsBO();
				bo.setQuestion_id(resultSet.getInt("question_id"));
				bo.setQuestion_no(resultSet.getInt("question_no"));
				bo.setSublesson_id(resultSet.getInt("sublesson_id"));
				list.add(bo);
			}

		}

		catch (Exception ex) {
			throw ex;
		} finally {
			try {
				if (connection != null) {
					connection.close();

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return list;
	}

	/** Sairam Section Begin **/

	/**
	 * @author rrajulapati
	 * @param jsonObject
	 * @throws ConnectionException
	 * @throws DBException
	 * @throws JSONException
	 */
	public void requiredTableDataInJSONFormate(JSONObject jsonObject)
			throws ConnectionException, DBException, JSONException {
		String quiree = jsonObject.getString("inputQuiree");
		JSONArray array = DBUtil.convertResultSetIntoJSONFormate(quiree);
		jsonObject.put("isDataAvilable", array != null);

		if (array != null) {
			jsonObject.put("tableData", array).put("rowCount",
					array.length());
		}
		else{
			jsonObject.put("rowCount",0);
		}
	//	System.out.println("\n B4 Return from util method the json Object in string formate is; \n\n\n\n "+jsonObject.toString());

	}

	/**
	 * @author rrajulapati
	 * @param jsonObject
	 * @throws JSONException
	 * @throws ConnectionException
	 * @throws DBException
	 */
	public void executeDeleteQuiree(JSONObject jsonObject)
			throws JSONException, ConnectionException, DBException {
		String quiree = jsonObject.getString("inputQuiree");
		// System.out.println("\n\n Input Quiree for Delete is; \n\t "+quiree);
		Connection connection = null;
		Statement st = null;
		connection = DatabaseConnection.getCrunchPrepConnection();
		try {
			st = connection.createStatement();
			int deleteCount = st.executeUpdate(quiree);
			jsonObject.put("deleteStatus",
					deleteCount == 0 ? "No Recode is Deleted"
							: "Deleted Successfully");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DBException(Exceptions.SQL_EXCEPTION, e);
		} finally {
			DBUtil.closeConnection(connection);
		}
	}

	/**
	 * @author rrajulapati
	 * @param questionsUploadBO
	 * @throws ConnectionException
	 * @throws DBException
	 */

	public void verbalQuestionUpDating(QuestionsUploadBO questionsUploadBO)
			throws ConnectionException, DBException {
		Connection connection = null;
		Statement st = null;
		String questionMasterQuiree = "UPDATE `cp_question_masters` SET `question_title`=?,"
				+ " `question_directions`=? WHERE  `question_id`=?";
		String questionQuiree = "UPDATE `cp_questions` SET "
				+ "`question`=?,"
				+ " `solution_text`=?, "
				+ "`solution_video`=?, `access_type`=?, `referral`=?, `status`=? WHERE  `question_id`=?";
		String questionAnswersQuiree = "UPDATE `cp_question_answers` SET `answer`=? WHERE   `question_id`=? and `answer_no`=?";
		String questionChoicesQuiree = "UPDATE `cp_question_choices` SET `choice`=?, `choice_tooltip`=? WHERE  `question_id`=? and `choice_no`=?";

		java.sql.PreparedStatement questionMaster = null;
		java.sql.PreparedStatement question = null;
		java.sql.PreparedStatement questionAnswers = null;
		java.sql.PreparedStatement questionChoices = null;

		try {
			connection = DatabaseConnection.getCrunchPrepConnection();
			connection.setAutoCommit(false);
			questionMaster = connection.prepareStatement(questionMasterQuiree);
			questionMaster.setString(1, questionsUploadBO.getQuestion_title());
			questionMaster.setString(2,
					questionsUploadBO.getQuestion_directions());
			questionMaster.setInt(3, questionsUploadBO.getQuestion_id());
			questionMaster.addBatch();
			question = connection.prepareStatement(questionQuiree);
			question.setString(1, questionsUploadBO.getQuestion());
			question.setString(2, questionsUploadBO.getSolution_text());
			question.setString(3, questionsUploadBO.getSolution_video());
			question.setString(4, questionsUploadBO.getFeeType());
			question.setString(5, questionsUploadBO.getRefferal());
			question.setString(6, questionsUploadBO.getStatus());
			question.setInt(7, questionsUploadBO.getQuestion_id());
			question.addBatch();
			questionAnswers = connection
					.prepareStatement(questionAnswersQuiree);

			int index = 1;
			for (String answer : questionsUploadBO.getAnswers()) {
				if (!answer.isEmpty()) {
					questionAnswers.setString(1, answer);
					questionAnswers.setInt(2,
							questionsUploadBO.getQuestion_id());
					questionAnswers.setInt(3, index++);
					questionAnswers.addBatch();

				}
			}
			index = 0;
			questionChoices = connection
					.prepareStatement(questionChoicesQuiree);
			for (String choices : questionsUploadBO.getChoices()) {
				if (!choices.isEmpty()) {
					questionChoices.setString(1, choices);
					String toolTip = "";
					if (questionsUploadBO.getTooltips() != null)
						toolTip = questionsUploadBO.getTooltips().get(index);
					questionChoices.setString(2, toolTip);
					questionChoices.setInt(3,
							questionsUploadBO.getQuestion_id());
					questionChoices.setInt(4, (++index));
					questionChoices.addBatch();

				}
			}

			int[] questionMasterStatus = questionMaster.executeBatch();
			int[] questionStatus = question.executeBatch();
			int[] questionAnswersStatus = questionAnswers.executeBatch();
			int[] questionChoicesStatus = questionChoices.executeBatch();
			if (questionMasterStatus.length >= 0 && questionStatus.length >= 0
					&& questionAnswersStatus.length >= 0
					&& questionChoicesStatus.length >= 0) {
				connection.setAutoCommit(true);
				questionsUploadBO.setOperationSuccess(true);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			e.printStackTrace();
			throw new DBException(Exceptions.SQL_EXCEPTION, e);
		} finally {
			DBUtil.closeConnection(connection);
		}
	}

	/**
	 * @author rrajulapati
	 * @param questionsUploadBO
	 * @throws ConnectionException
	 * @throws DBException
	 */
	public void verbalQuestionUploading(QuestionsUploadBO questionsUploadBO)
			throws ConnectionException, DBException {
		Connection connection = null;
		Statement st = null;
		String questionMasterQuiree = "INSERT INTO `cp_question_masters`"
				+ " (`section_id`, `category_id`, `type_id`, `test_id`, `question_title`, `question_directions`) "
				+ "VALUES (?, ?, ?, ?, ?, ?)";
		String questionQuiree = "INSERT INTO `cp_questions`"
				+ " (`question_id`, `skill_id1`, `diff_id`, `question_no`, `question`, `solution_text`, `solution_video`, `access_type`, `referral`,`status`,`skill_id2`,`skill_id3`) "
				+ "VALUES (?,?,?,1,?,?,?,?,?,?,?,?)";
		String questionAnswersQuiree = "INSERT INTO `cp_question_answers` (`question_id`, `question_no`, `answer_no`, `answer`) "
				+ "VALUES (?, 1, ?,?)";
		String questionChoicesQuiree = "INSERT INTO `cp_question_choices` (`question_id`, `question_no`, `choice_no`, `choice`, `choice_tooltip`)"
				+ " VALUES (?, 1, ?, ?,?)";
		java.sql.PreparedStatement questionMaster = null;
		java.sql.PreparedStatement question = null;
		java.sql.PreparedStatement questionAnswers = null;
		java.sql.PreparedStatement questionChoices = null;

		try {
			connection = DatabaseConnection.getCrunchPrepConnection();
			connection.setAutoCommit(false);
			questionMaster = connection.prepareStatement(questionMasterQuiree,
					java.sql.PreparedStatement.RETURN_GENERATED_KEYS);
			questionMaster.setInt(1, questionsUploadBO.getSection_id());
			questionMaster.setInt(2, questionsUploadBO.getCategory_id());
			questionMaster.setInt(3, questionsUploadBO.getTypeId());
			questionMaster.setInt(4, questionsUploadBO.getTest_id());
			questionMaster.setString(5, questionsUploadBO.getQuestion_title());
			questionMaster.setString(6,
					questionsUploadBO.getQuestion_directions());
			if (questionMaster.executeUpdate() > 0) {

				ResultSet keys = questionMaster.getGeneratedKeys();
				keys.next();
				questionsUploadBO.setQuestion_id(keys.getInt(1));
				keys.close();
				question = connection.prepareStatement(questionQuiree,
						PreparedStatement.RETURN_GENERATED_KEYS);
				question.setInt(1, questionsUploadBO.getQuestion_id());
				question.setString(2, questionsUploadBO.getSkills().get(0));
				question.setInt(3, questionsUploadBO.getDifficulty_id());
				question.setString(4, questionsUploadBO.getQuestion());
				question.setString(5, questionsUploadBO.getSolution_text());
				question.setString(6, questionsUploadBO.getSolution_video());
				question.setString(7, questionsUploadBO.getFeeType());

				question.setString(8, questionsUploadBO.getRefferal());
				question.setString(9, questionsUploadBO.getStatus());
				question.setString(
						10,
						questionsUploadBO.getSkills().size() > 1 ? questionsUploadBO
								.getSkills().get(1) : null);
				question.setString(
						11,
						questionsUploadBO.getSkills().size() > 2 ? questionsUploadBO
								.getSkills().get(2) : null);
				question.addBatch();
				questionAnswers = connection.prepareStatement(
						questionAnswersQuiree,
						PreparedStatement.RETURN_GENERATED_KEYS);

				int index = 1;
				for (String answer : questionsUploadBO.getAnswers()) {
					if (!answer.isEmpty()) {
						questionAnswers.setInt(1,
								questionsUploadBO.getQuestion_id());
						questionAnswers.setInt(2, index);
						questionAnswers.setString(3, answer);
						questionAnswers.addBatch();
						index++;
					}
				}
				index = 0;
				questionChoices = connection.prepareStatement(
						questionChoicesQuiree,
						PreparedStatement.RETURN_GENERATED_KEYS);
				for (String choices : questionsUploadBO.getChoices()) {
					if (!choices.isEmpty()) {
						questionChoices.setInt(1,
								questionsUploadBO.getQuestion_id());
						questionChoices.setInt(2, (index + 1));
						questionChoices.setString(3, choices);
						String toolTip = "";
						if (questionsUploadBO.getTooltips() != null)
							toolTip = questionsUploadBO.getTooltips()
									.get(index);
						questionChoices.setString(4, toolTip);
						questionChoices.addBatch();
						index++;
					}
				}

				int[] questionMasterStatus = questionMaster.executeBatch();
				int[] questionStatus = question.executeBatch();
				int[] questionAnswersStatus = questionAnswers.executeBatch();
				int[] questionChoicesStatus = questionChoices.executeBatch();
				if (questionMasterStatus.length >= 0
						&& questionStatus.length >= 0
						&& questionAnswersStatus.length >= 0
						&& questionChoicesStatus.length >= 0) {
					connection.setAutoCommit(true);
					questionsUploadBO.setOperationSuccess(true);

				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DBException(Exceptions.SQL_EXCEPTION, e);
		} finally {
			DBUtil.closeConnection(connection);
		}

	}

	/** Sairam Section End **/

	/**
	 * @author kkatikala
	 * @param questionsUploadBO
	 * @throws ConnectionException
	 * @throws DBException
	 */
	public void quantComparisionUploading(QuestionsUploadBO questionsUploadBO)
			throws ConnectionException, DBException {
		Connection connection = null;
		Statement st = null;
		String questionMasterQuiree = "INSERT INTO `cp_question_masters`"
				+ " (`section_id`, `category_id`, `type_id`, `test_id`, `question_title`, `question_directions`) "
				+ "VALUES (?, ?, ?, ?, ?, ?)";
		String questionQuiree = "INSERT INTO `cp_questions`"
				+ " (`question_id`, `skill_id1`,`skill_id2`,`skill_id3`, `diff_id`, `question_no`, `question`, `quantityA`,`quantityB`,`solution_text`, `solution_video`, `access_type`, `referral`,`status`) "
				+ "VALUES (?, ?, ?,?,?, 1, ?,?,?, ?, ?, ?, ?,?)";
		String questionAnswersQuiree = "INSERT INTO `cp_question_answers` (`question_id`, `question_no`, `answer_no`, `answer`) "
				+ "VALUES (?, 1, ?,?)";
		String questionChoicesQuiree = "INSERT INTO `cp_question_choices` (`question_id`, `question_no`, `choice_no`, `choice`, `choice_tooltip`)"
				+ " VALUES (?, 1, ?, ?,?)";
		String question_lessons_query = "INSERT INTO `cp_question_lessons` (`question_id`, `sublesson_id`, `lesson_no`, `question_no`)"
				+ " VALUES (?,?, ?,?)";

		java.sql.PreparedStatement questionMaster = null;
		java.sql.PreparedStatement question = null;
		java.sql.PreparedStatement questionAnswers = null;
		java.sql.PreparedStatement questionChoices = null;
		java.sql.PreparedStatement questionLessons = null;
		try {
			connection = DatabaseConnection.getCrunchPrepConnection();
			connection.setAutoCommit(false);
			questionMaster = connection.prepareStatement(questionMasterQuiree,
					java.sql.PreparedStatement.RETURN_GENERATED_KEYS);
			questionMaster.setInt(1, questionsUploadBO.getSection_id());
			questionMaster.setInt(2, questionsUploadBO.getCategory_id());
			questionMaster.setInt(3, questionsUploadBO.getTypeId());
			questionMaster.setInt(4, questionsUploadBO.getTest_id());
			questionMaster.setString(5, questionsUploadBO.getQuestion_title());
			questionMaster.setString(6,
					questionsUploadBO.getQuestion_directions());
			if (questionMaster.executeUpdate() > 0) {

				ResultSet keys = questionMaster.getGeneratedKeys();
				keys.next();
				questionsUploadBO.setQuestion_id(keys.getInt(1));
				keys.close();
				question = connection.prepareStatement(questionQuiree,
						PreparedStatement.RETURN_GENERATED_KEYS);
				question.setInt(1, questionsUploadBO.getQuestion_id());

				question.setString(
						2,
						questionsUploadBO.getSkills().size() >= 1 ? questionsUploadBO
								.getSkills().get(0) : null);
				question.setString(
						3,
						questionsUploadBO.getSkills().size() >= 2 ? questionsUploadBO
								.getSkills().get(1) : null);
				question.setString(
						4,
						questionsUploadBO.getSkills().size() >= 3 ? questionsUploadBO
								.getSkills().get(2) : null);
				question.setInt(5, questionsUploadBO.getDifficulty_id());
				question.setString(6, questionsUploadBO.getQuestion());
				question.setString(7, questionsUploadBO.getQuantityA());
				question.setString(8, questionsUploadBO.getQuantityB());
				question.setString(9, questionsUploadBO.getSolution_text());
				question.setString(10, questionsUploadBO.getSolution_video());
				question.setString(11, questionsUploadBO.getFeeType());
				question.setString(12, questionsUploadBO.getRefferal());
				question.setString(13, questionsUploadBO.getStatus());
				question.addBatch();
				questionAnswers = connection.prepareStatement(
						questionAnswersQuiree,
						PreparedStatement.RETURN_GENERATED_KEYS);

				int index = 1;
				for (String answer : questionsUploadBO.getAnswers()) {
					if (!answer.isEmpty()) {
						questionAnswers.setInt(1,
								questionsUploadBO.getQuestion_id());
						questionAnswers.setInt(2, index);
						questionAnswers.setString(3, answer);
						questionAnswers.addBatch();
						index++;
					}
				}
				index = 0;
				questionChoices = connection.prepareStatement(
						questionChoicesQuiree,
						PreparedStatement.RETURN_GENERATED_KEYS);
				for (String choices : questionsUploadBO.getChoices()) {
					if (!choices.isEmpty()) {
						questionChoices.setInt(1,
								questionsUploadBO.getQuestion_id());
						questionChoices.setInt(2, (index + 1));
						questionChoices.setString(3, choices);
						String toolTip = "";
						if (questionsUploadBO.getTooltips() != null)
							toolTip = questionsUploadBO.getTooltips()
									.get(index);
						questionChoices.setString(4, toolTip);
						questionChoices.addBatch();
						index++;
					}
				}

				index = 0;
				questionLessons = connection.prepareStatement(
						question_lessons_query,
						PreparedStatement.RETURN_GENERATED_KEYS);
				for (String lessons : questionsUploadBO.getLessons()) {
					if (!lessons.isEmpty()) {
						questionLessons.setInt(1,
								questionsUploadBO.getQuestion_id());
						questionLessons.setString(2, lessons);
						questionLessons.setInt(3, (index + 1));
						questionLessons.setString(4, 1 + "");
						questionLessons.addBatch();
						index++;
					}
				}

				int[] questionMasterStatus = questionMaster.executeBatch();
				int[] questionStatus = question.executeBatch();
				int[] questionAnswersStatus = questionAnswers.executeBatch();
				int[] questionChoicesStatus = questionChoices.executeBatch();
				int[] questionLessonsStatus = questionLessons.executeBatch();
				if (questionMasterStatus.length >= 0
						&& questionStatus.length >= 0
						&& questionAnswersStatus.length >= 0
						&& questionChoicesStatus.length >= 0
						&& questionLessonsStatus.length >= 0) {
					connection.setAutoCommit(true);
					questionsUploadBO.setOperationSuccess(true);

				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DBException(Exceptions.SQL_EXCEPTION, e);
		} finally {
			DBUtil.closeConnection(connection);
		}

	}

	/**
	 * @author kkatikala
	 * @param questionsUploadBO
	 * @throws ConnectionException
	 * @throws DBException
	 */

	public void quantComparisionUpDating(QuestionsUploadBO questionsUploadBO)
			throws ConnectionException, DBException {
		Connection connection = null;
		Statement st = null;
		String questionMasterQuiree = "UPDATE `cp_question_masters` SET `question_title`=?,"
				+ " `question_directions`=? WHERE  `question_id`=?";
		String questionQuiree = "UPDATE `cp_questions` SET "
				+ "`question`=?,"
				+ " `solution_text`=?, "
				+ "`solution_video`=?, `access_type`=?, `referral`=?, `status`=?,`skill_id1`=?,"
				+ " `skill_id2`=?,`skill_id3`=?,`diff_id`=?,`quantityA`=?,`quantityB`=? WHERE  `question_id`=?";
		String questionAnswersQuiree = "UPDATE `cp_question_answers` SET `answer`=? WHERE   `question_id`=? and `answer_no`=?";
		String questionChoicesQuiree = "UPDATE `cp_question_choices` SET `choice`=?, `choice_tooltip`=? WHERE  `question_id`=? and `choice_no`=?";

		String questionLessonsDelete = "DELETE from `cp_question_lessons` WHERE  `question_id`=? and `question_no`=?";
		// String questionLessonsQuery =
		// "UPDATE `cp_question_lessons` SET `sublesson_id`=? WHERE  `question_id`=? and `question_no`=? and lesson_no=?";
		String question_lessons_query = "INSERT INTO `cp_question_lessons` (`question_id`, `sublesson_id`, `lesson_no`, `question_no`,`master_lesson`)"
				+ " VALUES (?,?, ?,?,?)";
		java.sql.PreparedStatement questionMaster = null;
		java.sql.PreparedStatement question = null;
		java.sql.PreparedStatement questionAnswers = null;
		java.sql.PreparedStatement questionChoices = null;
		java.sql.PreparedStatement questionLessons = null;
		java.sql.PreparedStatement questionLessonsInsert = null;

		try {
			connection = DatabaseConnection.getCrunchPrepConnection();
			connection.setAutoCommit(false);
			questionMaster = connection.prepareStatement(questionMasterQuiree);
			questionMaster.setString(1, questionsUploadBO.getQuestion_title());
			questionMaster.setString(2,
					questionsUploadBO.getQuestion_directions());
			questionMaster.setInt(3, questionsUploadBO.getQuestion_id());
			questionMaster.addBatch();
			question = connection.prepareStatement(questionQuiree);
			question.setString(1, questionsUploadBO.getQuestion());
			question.setString(2, questionsUploadBO.getSolution_text());
			question.setString(3, questionsUploadBO.getSolution_video());
			question.setString(4, questionsUploadBO.getFeeType());
			question.setString(5, questionsUploadBO.getRefferal());
			question.setString(6, questionsUploadBO.getStatus());
			question.setString(
					7,
					questionsUploadBO.getSkills().size() >= 1 ? questionsUploadBO
							.getSkills().get(0) : null);
			question.setString(
					8,
					questionsUploadBO.getSkills().size() >= 2 ? questionsUploadBO
							.getSkills().get(1) : null);
			question.setString(
					9,
					questionsUploadBO.getSkills().size() >= 3 ? questionsUploadBO
							.getSkills().get(2) : null);
			question.setInt(10, questionsUploadBO.getDifficulty_id());
			question.setString(11, questionsUploadBO.getQuantityA());
			question.setString(12, questionsUploadBO.getQuantityB());

			question.setInt(13, questionsUploadBO.getQuestion_id());
			question.addBatch();
			questionAnswers = connection
					.prepareStatement(questionAnswersQuiree);

			int index = 1;
			for (String answer : questionsUploadBO.getAnswers()) {
				if (!answer.isEmpty()) {
					questionAnswers.setString(1, answer);
					questionAnswers.setInt(2,
							questionsUploadBO.getQuestion_id());
					questionAnswers.setInt(3, index++);
					questionAnswers.addBatch();

				}
			}
			index = 0;
			questionChoices = connection
					.prepareStatement(questionChoicesQuiree);
			for (String choices : questionsUploadBO.getChoices()) {
				if (!choices.isEmpty()) {
					questionChoices.setString(1, choices);
					String toolTip = "";
					if (questionsUploadBO.getTooltips() != null)
						toolTip = questionsUploadBO.getTooltips().get(index);
					questionChoices.setString(2, toolTip);
					questionChoices.setInt(3,
							questionsUploadBO.getQuestion_id());
					questionChoices.setInt(4, (++index));
					questionChoices.addBatch();

				}
			}

			index = 0;
			questionLessons = connection
					.prepareStatement(questionLessonsDelete);
			questionLessons.setInt(1, questionsUploadBO.getQuestion_id());
			questionLessons.setInt(2, 1);
			questionLessons.addBatch();

			index = 0;
			questionLessonsInsert = connection
					.prepareStatement(question_lessons_query);
			for (String lessons : questionsUploadBO.getLessons()) {
				if (!lessons.isEmpty()) {

					if (index == 0) {
						questionLessonsInsert.setInt(1,
								questionsUploadBO.getQuestion_id());
						questionLessonsInsert.setString(2, lessons);
						questionLessonsInsert.setInt(3, (index + 1));
						questionLessonsInsert.setString(4, 1 + "");
						questionLessonsInsert.setString(5, "YES");
						questionLessonsInsert.addBatch();
						index++;
					} else {
						questionLessonsInsert.setInt(1,
								questionsUploadBO.getQuestion_id());
						questionLessonsInsert.setString(2, lessons);
						questionLessonsInsert.setInt(3, (index + 1));
						questionLessonsInsert.setString(4, 1 + "");
						questionLessonsInsert.setString(5, null);
						questionLessonsInsert.addBatch();
						index++;
					}
				}
			}

			int[] questionMasterStatus = questionMaster.executeBatch();
			int[] questionStatus = question.executeBatch();
			int[] questionAnswersStatus = questionAnswers.executeBatch();
			int[] questionChoicesStatus = questionChoices.executeBatch();
			int[] questionLessonsDeleteStatus = questionLessons.executeBatch();
			int[] questionLessonsStatus = questionLessonsInsert.executeBatch();
			if (questionMasterStatus.length >= 0 && questionStatus.length >= 0
					&& questionAnswersStatus.length >= 0
					&& questionChoicesStatus.length >= 0
					&& questionLessonsDeleteStatus.length >= 0
					&& questionLessonsStatus.length >= 0) {
				connection.setAutoCommit(true);
				questionsUploadBO.setOperationSuccess(true);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			e.printStackTrace();
			throw new DBException(Exceptions.SQL_EXCEPTION, e);
		} finally {
			DBUtil.closeConnection(connection);
		}
	}

	/** End of QUant comparisions **/

	/** Deepthi Start **/
	@Override
	public void saveAwaQuestion(QuestionsUploadBO questionsUploadBO)
			throws ConnectionException, DBException {
		Connection connection = null;
		Statement st = null;
		// System.out.println("in awa adding");

		String query = "select * from cp_awa_questions aw where aw.awa_question_id like '"
				+ questionsUploadBO.getQuestion_id() + "'";

		// System.out.println("The Query is" + query);
		try {
			connection = DatabaseConnection.getCrunchPrepConnection();
			st = connection.createStatement();
			String awaquestion = "";
			ResultSet rs = st.executeQuery(query);
			if (rs.next()) {

				awaquestion = "UPDATE `cp_awa_questions` SET `category_id`="
						+ questionsUploadBO.getCategory_id()
						+ ", `awa_question_topic`='"
						+ questionsUploadBO.getQuestion_type()
						+ "', `awa_question_directions`='"
						+ questionsUploadBO.getQuestion_directions()
						+ "', `access_type`='"
						+ questionsUploadBO.getAccess_type() + "', `status`='"
						+ questionsUploadBO.getStatus()
						+ "' WHERE  `awa_question_id`="
						+ questionsUploadBO.getQuestion_id() + "";
			} else {

				awaquestion = "INSERT INTO `cp_awa_questions` (`test_master_id`, `section_id`, `category_id`, `awa_question_topic`, `awa_question_directions`, `access_type`, `status`) "
						+ "VALUES ( "
						+ questionsUploadBO.getTest_id()
						+ ","
						+ questionsUploadBO.getSection_id()
						+ ", "
						+ questionsUploadBO.getCategory_id()
						+ ", '"
						+ questionsUploadBO.getQuestion_type()
						+ "', '"
						+ questionsUploadBO.getQuestion_directions()
						+ "', '"
						+ questionsUploadBO.getAccess_type()
						+ "', '"
						+ questionsUploadBO.getStatus() + "')";
			}
			// System.out.println("The query is"+awaquestion);
			int i = st.executeUpdate(awaquestion);
			if (i > 0) {
				questionsUploadBO.setOperationSuccess(true);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DBException(e);
		} finally {
			DBUtil.closeConnection(connection);
		}
	}

	/**
	 * Deepthi End
	 * 
	 * @throws ConnectionException
	 * @throws DBException
	 **/

	public void getAwaquestionsList(JSONObject jsonObject)
			throws ConnectionException, DBException, JSONException {

		String quiree = "select aw.awa_question_id,aw.test_master_id,aw.section_id,aw.category_id,aw.awa_question_topic,aw.awa_question_directions,aw.access_type,aw.`status`,ca.category_name from cp_awa_questions aw,cp_question_categories ca where ca.category_id=aw.category_id ";
		JSONArray array = DBUtil.convertResultSetIntoJSONFormate(quiree);
		jsonObject.put("isDataAvilable", array != null);
		if (array != null)
			jsonObject.put("tableData", array.toString());

		// JSONArray ary=jsonObject.getJSONArray("");
	}

	@Override
	public String deleteAwaQuestion(String parameter)
			throws ConnectionException, DBException {
		Connection connection = null;
		Statement st = null;
		// System.out.println("in awa adding");
		String msg = "error";
		String awaquestion = "delete  from cp_awa_questions  where awa_question_id in("
				+ parameter + ");";
		// System.out.println("The Query is" + awaquestion);
		try {
			connection = DatabaseConnection.getCrunchPrepConnection();
			st = connection.createStatement();
			int i = st.executeUpdate(awaquestion);
			if (i > 0) {
				msg = "true";
				return msg;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DBException(Exceptions.SQL_EXCEPTION, e);
		} finally {
			DBUtil.closeConnection(connection);
		}
		return msg;
	}
}
