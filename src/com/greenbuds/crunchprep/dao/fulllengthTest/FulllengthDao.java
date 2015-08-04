package com.greenbuds.crunchprep.dao.fulllengthTest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import net.sf.json.JSON;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.greenbuds.crunchprep.bo.fulllengthtest.FullLengthTestBO;
import com.greenbuds.crunchprep.bo.fulllengthtest.FullResultsBO;
import com.greenbuds.crunchprep.bo.fulllengthtest.OverallPerformanceBO;
import com.greenbuds.crunchprep.bo.fulllengthtest.QuestionAnalysisBO;
import com.greenbuds.crunchprep.bo.fulllengthtest.ScoreBO;
import com.greenbuds.crunchprep.bo.registration.LoginUserBO;
import com.greenbuds.crunchprep.bo.server.ServerProperties;
import com.greenbuds.crunchprep.bo.contentcreator.QuestionsUploadBO;
import com.greenbuds.crunchprep.data.DatabaseConnection;
import com.greenbuds.crunchprep.exception.CPException;
import com.greenbuds.crunchprep.exception.CommonExceptions;
import com.greenbuds.crunchprep.exception.ConnectionException;
import com.greenbuds.crunchprep.exception.DBException;
import com.greenbuds.crunchprep.setups.ReviewReason;
import com.greenbuds.crunchprep.setups.Section;
import com.greenbuds.crunchprep.util.DBUtil;

public class FulllengthDao implements IFulllengthDao {

	private static FulllengthDao fulllengthDAO;

	private FulllengthDao() {

	}

	public static synchronized FulllengthDao getInstance() {
		if (fulllengthDAO == null)
			fulllengthDAO = new FulllengthDao();
		return fulllengthDAO;
	}

	@Override
	@SuppressWarnings("all")
	/**
	 * used to get the questions for the Full length test sections(verbal/Quant) 
	 * @param section is Enum Type of Quant/Verbal
	 *        object is the Json object which contains the difficulty range with the variable name as limit
	 * @return Arraylist of questions
	 * @throws ConnectionException,CommonException
	 * 
	 */
	public List<QuestionsUploadBO> getQuestionsForFulllegthTest(
			Section section, JSONObject object, int userId)
			throws ConnectionException, CommonExceptions, JSONException {
		LinkedList<Integer> questions = null;
		// System.out.println("section : "+section.getSectionId());
		int sectionNumber = 1;// =object.getInt("sectionNumber");
		 
	
		int testNumber = 0;
		JSONObject sectionTwo = new JSONObject();
		Statement st = null;
		ResultSet rs = null;
		Connection connection = null;
		int fullLengthRowId=0;
		sectionNumber = object.getInt("sectionNumber");
		if(sectionNumber!=1)
		{
		  fullLengthRowId = (int)object.get("fullLengthRowId");// =object.getInt("fullLengthRowId");
		}
		if (section.getSectionId() == 1) {

			try {
				com.greenbuds.crunchprep.util.MathQuestions.getMathQuestions(
						object, userId);
				if (sectionNumber == 1) {
					System.out
							.println("##################Generating QUANT  Questions for the First Section");
					sectionTwo.put("limit", object.getDouble("limit"));
					new com.greenbuds.crunchprep.util.Verbal_Questions()
							.getVerbalQuestions(sectionTwo, userId);
				} else {

					if (section.getSectionId() == 1) {
						com.greenbuds.crunchprep.util.MathQuestions
								.getMathQuestions(object, userId);
					} else {
						new com.greenbuds.crunchprep.util.Verbal_Questions()
								.getVerbalQuestions(object, userId);
					}

				}
			} catch (DBException e) {

				e.printStackTrace();
			}

		} else if (section.getSectionId() == 2) {

			try {
				new com.greenbuds.crunchprep.util.Verbal_Questions()
						.getVerbalQuestions(object, userId);
				if (sectionNumber == 1) {
					System.out
							.println("##################Generating VERBAL  Questions for the First Section");

					sectionTwo.put("limit", object.getDouble("limit"));
					com.greenbuds.crunchprep.util.MathQuestions
							.getMathQuestions(sectionTwo, userId);
				} else {
					System.out
							.println("##################Generating VERBAL Questions for Second Section");

					if (section.getSectionId() == 1) {
						com.greenbuds.crunchprep.util.MathQuestions
								.getMathQuestions(object, userId);
					} else {
						new com.greenbuds.crunchprep.util.Verbal_Questions()
								.getVerbalQuestions(object, userId);
					}

				}

			} catch (DBException e) {

				e.printStackTrace();
			}
		}
		try {

			System.out
					.println("\n\n\n\n\n Section Number is; " + sectionNumber);
			// fullLengthRowId=object.getInt("fullLengthRowId");

			testNumber = object.getInt("testNumber");

			boolean isQuestionsAvilable = object
					.getBoolean("isQuestionAvilable");
			System.out.println("\n isQuetiion Avialbi is: "
					+ isQuestionsAvilable);
			JSONArray array = null;
			if (isQuestionsAvilable) {
				questions = new LinkedList<Integer>();
				array = object.getJSONArray("tableData");
				System.out.println("\nArraylength is; " + array.length());
				System.out.println("\n\n\nPrepared Quiree is;  \n\t"
						+ object.get("tableData"));
				JSONObject jsonObject = null;
				for (int index = 0; questions.size() < array.length();) {
					jsonObject = array.getJSONObject(index++);
					// /System.out.println("\n question id is : "+jsonObject.getInt("question_id")+" index is "+index++);
					questions.add(jsonObject.getInt("question_id"));
				}
				System.out.println("\n Questions List is; " + questions
						+ "\n Questions SIze is: " + questions.size());
			} else {
				questions = new LinkedList<Integer>();
				System.out.println("QUestions not available");
				return null;
			}
		} catch (JSONException e) {

			e.printStackTrace();
		}

		java.sql.ResultSet resultSet1 = null;
		Statement stmt1 = null;
		String fullLengthTestQuiree = "INSERT INTO `cp_fulllength_test`"
				+ " (`userId`, `testNo`, `section_Id`, `question_id`, `section_number`, `pattern`, `question_index`,`section_1`,`starting_difficulty`) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?,?,?)";

		String query_insert_toTestResults = "INSERT INTO  `cp_test_results` (`userid`, `section_id`, `category_id`, `type_id`, `sublession_id`, "
				+ "                                       `test_master_id`, `question_id`, `diff_id`, `skill_id1`, `skill_id2`, `skill_id3`, `test_no`) "
				+ "                                          VALUES (?, ?,?,?, ?, ?, ?, ?, ?, ?, ?, ?)";
		if (sectionNumber != 1) {
			fullLengthTestQuiree = "UPDATE  `cp_fulllength_test` SET `section_"+ sectionNumber + "`=? WHERE  `flength_rowId`="+ fullLengthRowId; 
		}

		PreparedStatement fullLengthTest, insertion_to_result;

		List<QuestionsUploadBO> list = new ArrayList<QuestionsUploadBO>();
		try {
			connection = DatabaseConnection.getCrunchPrepConnection();
			st = connection.createStatement();//
			String sql = "";

			if (Section.QUANTITATIVE.getSectionId() == section.getSectionId()) {
				int patternNumber = object.getInt("generatedPattern");
				List<Integer> al1 = new ArrayList<Integer>();
				List<Integer> al2 = new ArrayList<Integer>();
				List<Integer> al3 = new ArrayList<Integer>();
				if (patternNumber == 1 || patternNumber == 2
						|| patternNumber == 3) {
					al1 = questions.subList(0, 13);
					if (patternNumber == 1) {
						al2 = questions.subList(13, 16);
						al3 = questions.subList(16, questions.size());
					} else {
						al2 = questions.subList(13, 17);
						al3 = questions.subList(17, questions.size());
					}
				} else {
					al1 = questions.subList(0, 14);
					al2 = questions.subList(14, 17);
					al3 = questions.subList(17, questions.size());
				}

				sql = "(select mast.* ,que.*,sec.*,cat.*,qtype.*,tmast.*,diff.*,qlessons.*,le.*,'' empty1,'' empty2,'' empty3,'' empty4,'' empty5 "
						+ " , (select grph.graph From cp_data_interpretation_graph grph where grph.graph_id like mast.graph_id) graph from cp_question_masters mast ,cp_questions que,cp_sections sec,cp_question_categories cat,cp_question_type qtype,"
						+ " cp_testmaster tmast,cp_difficulty_level diff,cp_question_lessons qlessons,cp_lessons le "
						+ " where mast.question_id in("
						+ al1
						+ ") and que.question_id=mast.question_id "
						+ " and sec.section_id=mast.section_id and cat.category_id=mast.category_id and "
						+ " qtype.question_type_id=mast.type_id and tmast.test_id=mast.test_id and "
						+ " diff.diff_id=que.diff_id and qlessons.question_id=mast.question_id and le.sublesson_id=qlessons.sublesson_id )"
						+ " union(select mast.* ,que.*,sec.*,cat.*,qtype.*,tmast.*,diff.* ,'' lessons_rowid,'' question_id,'' sublesson_id,'' lesson_no,'' question_no,'' master_lesson,'' comments,'' sublesson_id,'' lesson_id,'' lesson_name,'' skill_id1, '' skill_id2,'' skill_id3 ,'' lesson_text,'' lesson_video,'' avg_time,"
						+ "'' access_type,'' status,'' comments,'' empty1,'' empty2,'' empty3,'' empty4,'' empty5  , (select grph.graph"
						+ "  From cp_data_interpretation_graph grph where grph.graph_id like mast.graph_id) graph from cp_question_masters mast"
						+ "  ,cp_questions que,cp_sections sec,cp_question_categories cat,cp_question_type qtype, cp_testmaster tmast,"
						+ " cp_difficulty_level diff  where mast.question_id in("
						+ al2
						+ " ) and que.question_id=mast.question_id  and sec.section_id=mast.section_id and cat.category_id=mast.category_id and  qtype.question_type_id=mast.type_id and tmast.test_id=mast.test_id and  diff.diff_id=que.diff_id  ) "
						+ " union (select mast.* ,que.*,sec.*,cat.*,qtype.*,tmast.*,diff.*,qlessons.*,le.*,'' empty1,'' empty2,'' empty3,'' empty4,'' empty5 "
						+ " , (select grph.graph From cp_data_interpretation_graph grph where grph.graph_id like mast.graph_id) graph from cp_question_masters mast ,cp_questions que,cp_sections sec,cp_question_categories cat,cp_question_type qtype,"
						+ " cp_testmaster tmast,cp_difficulty_level diff,cp_question_lessons qlessons,cp_lessons le "
						+ " where mast.question_id in("
						+ al3
						+ ") and que.question_id=mast.question_id "
						+ " and sec.section_id=mast.section_id and cat.category_id=mast.category_id and "
						+ " qtype.question_type_id=mast.type_id and tmast.test_id=mast.test_id and "
						+ " diff.diff_id=que.diff_id and qlessons.question_id=mast.question_id and le.sublesson_id=qlessons.sublesson_id)";

			} else {
				sql = "SELECT mast.*,que.*,sec.*,cat.*,qtype.*,tmast.*,diff.*,'' empty1,'' empty2,'' empty3,'' empty4,'' empty5  FROM cp_question_masters mast,"
						+ " cp_questions que,cp_sections sec,cp_question_categories cat,cp_question_type qtype, cp_testmaster tmast,cp_difficulty_level diff "
						+ " WHERE mast.question_id IN("
						+ questions.subList(0, questions.size())
						+ ") AND que.question_id=mast.question_id AND sec.section_id=mast.section_id AND "
						+ " cat.category_id=mast.category_id AND 	qtype.question_type_id=mast.type_id AND tmast.test_id=mast.test_id AND 	 diff.diff_id=que.diff_id";
			}

			// String
			// sql="select mast.* ,que.*,sec.*,cat.*,qtype.*,tmast.*,diff.*,'' empty1,'' empty2,'' empty3,'' empty4,'' empty5  from cp_question_masters mast ,cp_questions que,cp_sections sec,cp_question_categories cat,cp_question_type qtype, cp_testmaster tmast,cp_difficulty_level diff where mast.question_id in(53,69,60,259,285,286,287,305,307,308,311,312,313,314,320,327,330,331,345) and que.question_id=mast.question_id  and sec.section_id=mast.section_id and cat.category_id=mast.category_id and  qtype.question_type_id=mast.type_id and tmast.test_id=mast.test_id and  diff.diff_id=que.diff_id"
			// ;
			sql = sql.replace("[", "").replace("]", "");
			System.out.println("list of questions generated for the test are %%%%%%%%%%%% "+questions);
			System.out.println("Questions Query is----------------------------------------------  : "	+ sql);
			stmt1 = connection.createStatement();
			insertion_to_result = connection
					.prepareStatement(query_insert_toTestResults);
			fullLengthTest = connection.prepareStatement(fullLengthTestQuiree,
					PreparedStatement.RETURN_GENERATED_KEYS);
			System.out.println("Questions : " + questions.size()
					+ "\t questions : " + questions);
			if (questions != null || questions.size() != 0) {
				if (sectionNumber == 1) {
					fullLengthTest.setInt(1, userId);
					fullLengthTest.setInt(2, testNumber);
					fullLengthTest.setInt(3, section.getSectionId());
					fullLengthTest.setInt(4, questions.get(0));
					fullLengthTest.setInt(5, 1);
					fullLengthTest.setString(6,
							section.getSectionId() == 1 ? "Q_V_Q_V_Q"
									: "V_Q_V_Q_V");
					fullLengthTest.setInt(7, 1);
					fullLengthTest.setString(
							8,
							questions.toString().replace("[", "(")
									.replace("]", ")"));
					fullLengthTest.setDouble(9, ServerProperties.getInistance()
							.getDifficulty());
					boolean isQuestionsAvilable = sectionTwo
							.getBoolean("isQuestionAvilable");
					if (isQuestionsAvilable) {
						ArrayList<Integer> questionss = new ArrayList<Integer>(
								20);
						JSONArray array = object.getJSONArray("tableData");
						JSONObject jsonObject = null;
						for (int index = 0; questions.size() < array.length();) {
							questions.add(jsonObject.getInt("question_id"));
						}

						 
					} else {
						 
					}
				} else {
					
					fullLengthTest.setString(1,	questions.toString().replace("[", "(").replace("]", ")"));
					System.out.println("calling from else for updating questions"+fullLengthTestQuiree);
				}

			}
			java.sql.ResultSet resultSet = st.executeQuery(sql);
			// System.out.println("Full Lenght Test  : "+fullLengthTestQuiree);
			while (resultSet.next()) {
				QuestionsUploadBO bo = new QuestionsUploadBO();
				bo.setSection_id(resultSet.getInt("section_id"));
				bo.setQuestion_id(resultSet.getInt("question_id"));

				bo.setUser_id(userId);
				bo.setTest_no(testNumber);

				bo.setCategory_id(resultSet.getInt("category_id"));
				bo.setTypeId(resultSet.getInt("type_id"));
				bo.setTest_id(resultSet.getInt("test_id"));
				bo.setLesson_id(section.getSectionId() == 1 ? resultSet
						.getInt("sublesson_id") : 0);
				bo.setPassageId(resultSet.getString("passage_id") != null ? resultSet
						.getInt("passage_id") : 0);
				if (bo.getPassageId() != 0) {
					String sql1 = "select passage.passage_title,passage.passage_type,passage.passage,passage.difficulty from cp_reading_comprehension_passage passage where passage.passage_id='"
							+ bo.getPassageId() + "'";
					resultSet1 = stmt1.executeQuery(sql1);
					while (resultSet1.next()) {
						bo.setPassageTitle(resultSet1
								.getString("passage_title"));
						bo.setPassage_type(resultSet1.getString("passage_type"));
						bo.setPassage(resultSet1.getString("passage"));
						bo.setPassage_difficultyId(resultSet1
								.getInt("difficulty"));
					}

				}
				bo.setGraphId(resultSet.getString("graph_id") != null ? resultSet
						.getInt("graph_id") : 0);
				if (bo.getGraphId() != 0) {
					String sql2 = "select graph.graph_title,graph.graph from cp_data_interpretation_graph graph where graph.graph_id='"
							+ bo.getGraphId() + "'";
					resultSet1 = stmt1.executeQuery(sql2);
					while (resultSet1.next()) {
						bo.setGraphTitle(resultSet1.getString("graph_title"));
						bo.setGraph(resultSet1.getString("graph"));
					}
				}
				bo.setQuestion_title(resultSet.getString("question_title"));
				bo.setQuestion_directions(resultSet
						.getString("question_directions"));
				bo.setSkillid1(resultSet.getInt("skill_id1"));
				bo.setSkillid2(resultSet.getInt("skill_id2"));
				bo.setSkillid3(resultSet.getInt("skill_id3"));
				bo.setDifficulty_id(resultSet.getInt("diff_id"));
				bo.setQuestion_no(resultSet.getString("question_no") != null ? resultSet
						.getInt("question_no") : 0);
				bo.setQuestion(resultSet.getString("question"));
				bo.setAvg_time(resultSet.getString("avg_time"));
				if (resultSet.getString("quantityA") != null) {
					bo.setQuantityA(resultSet.getString("quantityA"));
				}
				if (resultSet.getString("quantityB") != null) {
					bo.setQuantityB(resultSet.getString("quantityB"));
				}
				if (resultSet.getString("solution_text") != null) {
					bo.setSolution_text(resultSet.getString("solution_text"));
				}
				if (resultSet.getString("solution_video") != null) {
					bo.setSolution_video(resultSet.getString("solution_video"));
				}
				if (resultSet.getString("access_type") != null) {
					bo.setAccess_type(resultSet.getString("access_type"));
				}
				bo.setRefferal(resultSet.getString("referral"));
				bo.setStatus(resultSet.getString("status"));
				if (resultSet.getString("question_passage") != null) {
					bo.setQuestionPassage(resultSet
							.getString("question_passage"));
				}

				String sql4 = "select  answers.answer  from cp_question_masters qmas left join "
						+ " cp_question_answers answers on qmas.question_id=answers.question_id where qmas.question_id like "
						+ bo.getQuestion_id();
				resultSet1 = stmt1.executeQuery(sql4);
				// System.out.println("Answers Query : "+sql4);
				ArrayList<String> al1 = new ArrayList<String>();
				while (resultSet1.next()) {
					if (resultSet1.getString(1) != null) {
						al1.add(resultSet1.getString(1));
					}
				}

				bo.setAnswers(al1);
				bo.setAnswers_length(bo.getAnswers().size());
				// System.out.println(bo.getAnswers_length()+"\t "+bo.getAnswers());
				String sql3 = "select choices.choice  from cp_question_masters qmas left join "
						+ " cp_question_choices choices on qmas.question_id=choices.question_id where qmas.question_id like'"
						+ bo.getQuestion_id() + "'";
				resultSet1 = stmt1.executeQuery(sql3);
				// System.out.println("Choices Quewry : "+sql3);
				ArrayList<String> al = new ArrayList<String>();
				while (resultSet1.next()) {
					if (resultSet1.getString("choice") != null) {
						al.add(resultSet1.getString("choice"));
					}
				}

				bo.setChoices(al);
				bo.setChoices_length(bo.getChoices().size());
				// System.out.println(bo.getChoices_length()+"\t"+bo.getChoices());
				// String skill_arr[]=resultSet.getString("skills").split(",");
				// bo.setSkills(Arrays.asList(skill_arr));
				bo.setDifficulty_name(resultSet.getString("diff_name"));
				bo.setCategory_name(resultSet.getString("category_name"));
				bo.setUserAnswer(resultSet.getString("empty1").split(","));
				bo.setIsAnswered(resultSet.getString("empty2"));
				bo.setIsFlagged(resultSet.getString("empty3"));
				bo.setAnswerstatus(resultSet.getString("empty4"));
				bo.setUser_time(resultSet.getString("empty5"));
				bo.setReading_comprehension_text("");

				list.add(bo);
				// System.out.println("calling");
				insertion_to_result.setInt(1, bo.getUser_id());
				insertion_to_result.setInt(2, bo.getSection_id());
				insertion_to_result.setInt(3, bo.getCategory_id());
				insertion_to_result.setInt(4, bo.getTypeId());
				insertion_to_result.setString(5, bo.getLesson_id() == 0 ? null
						: String.valueOf(bo.getLesson_id()));
				// insertion_to_result.setInt(5,
				// bo.getLesson_id()==0?null:bo.getLesson_id());
				insertion_to_result.setInt(6, 2);
				insertion_to_result.setInt(7, bo.getQuestion_id());
				insertion_to_result.setInt(8, bo.getDifficulty_id());
				insertion_to_result.setInt(9, bo.getSkillid1());
				insertion_to_result.setString(10, bo.getSkillid2() == 0 ? null
						: String.valueOf(bo.getSkillid2()));
				insertion_to_result.setString(11, bo.getSkillid3() == 0 ? null
						: String.valueOf(bo.getSkillid3()));
				insertion_to_result.setInt(12, bo.getTest_no());
				insertion_to_result.addBatch();
				// System.out.println("Data adding");
			}

			
			
			//Inserting questions into fulllength table
			if (questions != null || questions.size() != 0) {
				boolean executed = fullLengthTest.execute();
				System.out.println("\n\n\n\n Section Numberis; "+ sectionNumber + "\n Execute is : " + executed);

				if (!executed && sectionNumber == 1) {
					resultSet = fullLengthTest.getGeneratedKeys();
					if (resultSet.next()) {
						// it's the unique rowId for identifing the row uniquly.
						System.out.println("Calling 11111st time");
						object.put("fullLengthRowId", resultSet.getInt(1));
					}
				}
			}
			int p[] = insertion_to_result.executeBatch();
			System.out.println("insertion_to_result.executeBatch()  "
					+ insertion_to_result.executeBatch());
			if (p.length == list.size()) {
				System.out
						.println("data inserted into test_results table successfully");
			} else {
				System.out
						.println("data not inserted into test_results table ");
			}

		} catch (SQLException e) {

			e.printStackTrace();
		} catch (JSONException e) {

			e.printStackTrace();
		}

		finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	public boolean markingQuestion(FullLengthTestBO bo) throws CPException {
		boolean flag = false;
		String result = "";
		Statement st = null;
		Connection connection = null;
		try {
			connection = DatabaseConnection.getCrunchPrepConnection();
			String query;

			st = connection.createStatement();
			query = "UPDATE cp_test_results r SET r.flagstatus='"
					+ bo.getFlag_status() + "' WHERE " + "r.userid="
					+ bo.getUserid() + " AND r.question_id="
					+ bo.getQuestion_id() + " AND " + "r.test_master_id="
					+ bo.getTest_id() + " AND r.test_no=" + bo.getTest_no();

			System.out.println(query);
			int i = st.executeUpdate(query);
			if (i >= 1) {
				flag = true;
			}
		} catch (Exception exception) {
			// exception.printStackTrace();

		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block

			}
		}

		return flag;
	}

	
	public void fullLengthTestQuestionSaving(JSONArray questionsData)
			throws ConnectionException, SQLException, JSONException {

		Connection connection = null;
		ResultSet rs = null;
		Statement st = null;

		try {
			connection = DatabaseConnection.getCrunchPrepConnection();
			String query = "";
			connection.setAutoCommit(false);
			String updatequery1 = "";
			String difficulty = "";

			String updatequery = "UPDATE `cp_test_results` SET `user_answer`=?,flagstatus=? ,result=?,user_time=?   WHERE  `userid`=? and section_id=? and  category_id=? and  type_id=? and test_master_id=? and test_no=? and question_id=?";

			int j = 0;
			if (questionsData.length() > 1) {
				j = questionsData.length() - 1;
				difficulty = ",section_"
						+ questionsData.getJSONObject(j).getInt(
								"currentsectionnumber")
						+ "_difficulty="
						+ questionsData.getJSONObject(j)
								.get("sectionDiffLevel") + "";
			}
			updatequery1 = "UPDATE `cp_fulllength_test` SET `section_Id`="
					+ questionsData.getJSONObject(j).getInt("Section_id")
					+ ",`question_id`="
					+ questionsData.getJSONObject(j).getInt("Question_id")
					+ " ,  section_number="
					+ questionsData.getJSONObject(j).getInt(
							"currentsectionnumber")
					+ " ,  question_index="
					+ questionsData.getJSONObject(j).getInt("Question_index")
					+ " , section_status='"
					+ questionsData.getJSONObject(j)
							.get("currentsectionstatus") + "' , status='"
					+ questionsData.getJSONObject(j).get("teststatus") + "',timeRemaining='"+questionsData.getJSONObject(j).getString("remainingTime")+"' "
					+ difficulty + "  where testNo="
					+ questionsData.getJSONObject(j).getInt("Test_no") + "  ";

			st = connection.createStatement();
			java.sql.PreparedStatement ps = connection.prepareStatement(updatequery);
			System.out.println("The Query is" + updatequery + "\nThe second Query is" + updatequery1);

			for (int i = 0; i < questionsData.length(); i++) {
				if (questionsData.getJSONObject(i).get("UserAnswer") instanceof JSONArray) {
					ps.setString(1,	questionsData.getJSONObject(i).getJSONArray("UserAnswer").toString().replace("},{", ",").replace("[", "").replace("]", "").replace("\"", ""));// user// answer	// array// to// stringconvertion   value   getting the first  index
					} else {
					ps.setString(1, "");
				}

				ps.setString(2,	questionsData.getJSONObject(i).getString("IsFlagged").isEmpty() ? "UNFLAG" : questionsData.getJSONObject(i).getString("IsFlagged"));
				ps.setString(3,	questionsData.getJSONObject(i).getString("Answerstatus").isEmpty() ? "SKIPPED": questionsData.getJSONObject(i).getString("Answerstatus"));
				ps.setString(4,	questionsData.getJSONObject(i).getString("User_time").isEmpty() ? "00:00:00" : questionsData.getJSONObject(i).getString("User_time"));
				ps.setInt(5, questionsData.getJSONObject(i).getInt("user_id"));
				ps.setInt(6, questionsData.getJSONObject(i).getInt("Section_id"));
				ps.setInt(7, questionsData.getJSONObject(i).getInt("Category_id"));
				ps.setInt(8, questionsData.getJSONObject(i).getInt("TypeId"));
				ps.setInt(9, questionsData.getJSONObject(i).getInt("Test_id"));
				ps.setInt(10, questionsData.getJSONObject(i).getInt("Test_no"));
				ps.setInt(11, questionsData.getJSONObject(i).getInt("Question_id"));
				ps.addBatch();
				System.out.println("Looping through the total json data" + ps);
			}

			ps.executeBatch();

			if (ps.executeBatch().length >= 0) {
				int i = st.executeUpdate(updatequery1);
				if (i > 0)
				{
					connection.commit();
					System.out.println("Difficulty level in fulllength test updated successflly");
				}
				else {
					connection.rollback();
					System.out.println("Difficulty Level in fulllength test not updated successflly");
				}
			} 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.close();
			}
		}

	}

	public boolean quittingTest(FullLengthTestBO bo) throws CPException {
		boolean flag = false;
		String result = "";
		Statement st = null;
		Connection connection = null;
		try {
			connection = DatabaseConnection.getCrunchPrepConnection();
			String query;
			st = connection.createStatement();
			query = "UPDATE cp_fulllength_test f SET f.question_id="+ bo.getQuestion_id() + ",f.question_index="+ bo.getQuestionIndex() + ",f.status='"+bo.getStatus()+"',f.timeRemaining='"+ bo.getTimeRemaining() + "' WHERE " + "f.userId="+ bo.getUserid() + " AND f.testNo=" + bo.getTest_no();
			System.out.println(query);
			int i = st.executeUpdate(query);
			if (i >= 1) {
				flag = true;
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block

			}
		}

		return flag;
	}

	/*
	 * public FullLengthTestBO verifyTest(LoginUserBO bo) throws CPException{
	 * boolean flag=false; String result=""; Statement st=null; ResultSet
	 * rs=null; Connection connection=null; int sectionid; FullLengthTestBO
	 * bo1=null; try{ connection=DatabaseConnection.getCrunchPrepConnection();
	 * String query;
	 * 
	 * 
	 * st=connection.createStatement();
	 * 
	 * query =
	 * "select t.section_number,t.section_Id from cp_fulllength_test t where t.`status`='INCOMPLETE' and t.userId="
	 * +bo.getUser_id();
	 * 
	 * System.out.println(query); rs = st.executeQuery(query); if(rs.next()) {
	 * bo1=new FullLengthTestBO();
	 * bo1.setSectionid(rs.getInt("section_number"));
	 * bo1.setSection_type(rs.getInt("section_Id")); query =
	 * "select t.section_"+bo1.getSectionid()+
	 * ",t.question_id,t.question_index,t.testNo from cp_fulllength_test t where t.`status`='INCOMPLETE' and t.userId="
	 * +bo.getUser_id()+" and t.section_number="+bo1.getSectionid();
	 * System.out.println(query); rs = st.executeQuery(query); if(rs.next()){
	 * bo1.setSection_questions(rs.getString(1));
	 * bo1.setQuestion_id(rs.getInt("question_id"));
	 * bo1.setQuestionIndex(rs.getInt("question_index"));
	 * bo1.setTest_no(rs.getInt("testNo")); } } } catch(Exception exception){
	 * //exception.printStackTrace();
	 * 
	 * 
	 * } finally{ try { if(connection!=null){ connection.close(); } } catch
	 * (SQLException e) { // TODO Auto-generated catch block
	 * 
	 * } }
	 * 
	 * return bo1; }
	 */

	public List<QuestionsUploadBO> resumeTest(FullLengthTestBO bo1)

	{
		Statement st = null;
		ResultSet rs = null;
		Connection connection = null;
		java.sql.ResultSet resultSet1 = null,resultSet2 = null;
		Statement stmt1 = null,stmt2 = null,stmt3=null;

		PreparedStatement fullLengthTest, insertion_to_result;

		List<QuestionsUploadBO> list = new ArrayList<QuestionsUploadBO>();
		String sql;
		try {
			connection = DatabaseConnection.getCrunchPrepConnection();
			st = connection.createStatement();
			stmt2 = connection.createStatement();
			
			//


			if (bo1.getSectionid() == 1) {

				/*sql = "select mast.* ,que.*,sec.*,cat.*,qtype.*,tmast.*,diff.*,qlessons.*,le.*,'' empty1,'' empty2,'' empty3,'' empty4,'' empty5 "
						+ " , (select grph.graph From cp_data_interpretation_graph grph where grph.graph_id like mast.graph_id) graph from cp_question_masters mast ,cp_questions que,cp_sections sec,cp_question_categories cat,cp_question_type qtype,"
						+ " cp_testmaster tmast,cp_difficulty_level diff,cp_question_lessons qlessons,cp_lessons le "
						+ " where mast.question_id in  "
						+ bo1.getSection_questions()
						+ " and que.question_id=mast.question_id "
						+ " and sec.section_id=mast.section_id and cat.category_id=mast.category_id and "
						+ " qtype.question_type_id=mast.type_id and tmast.test_id=mast.test_id and "
						+ " diff.diff_id=que.diff_id and qlessons.question_id=mast.question_id and le.sublesson_id=qlessons.sublesson_id ";
	*/		
				
				sql = "select mast.* ,que.*,sec.*,cat.*,qtype.*,tmast.*,diff.*, '' empty1,'' empty2,'' empty3,'' empty4,'' empty5 "
						+ " , (select grph.graph From cp_data_interpretation_graph grph where grph.graph_id like mast.graph_id) graph from cp_question_masters mast ,cp_questions que,cp_sections sec,cp_question_categories cat,cp_question_type qtype,"
						+ " cp_testmaster tmast,cp_difficulty_level diff  "
						+ " where mast.question_id in  "
						+ bo1.getSection_questions()
						+ " and que.question_id=mast.question_id "
						+ " and sec.section_id=mast.section_id and cat.category_id=mast.category_id and "
						+ " qtype.question_type_id=mast.type_id and tmast.test_id=mast.test_id and "
						+ " diff.diff_id=que.diff_id  ";

			} else {
				sql = "SELECT mast.*,que.*,sec.*,cat.*,qtype.*,tmast.*,diff.*,'' empty1,'' empty2,'' empty3,'' empty4,'' empty5  FROM cp_question_masters mast,"
						+ " cp_questions que,cp_sections sec,cp_question_categories cat,cp_question_type qtype, cp_testmaster tmast,cp_difficulty_level diff "
						+ " WHERE mast.question_id IN  "
						+ bo1.getSection_questions()
						+ " AND que.question_id=mast.question_id AND sec.section_id=mast.section_id AND "
						+ " cat.category_id=mast.category_id AND 	qtype.question_type_id=mast.type_id AND tmast.test_id=mast.test_id AND 	 diff.diff_id=que.diff_id";

			}
			// String
			// sql="select mast.* ,que.*,sec.*,cat.*,qtype.*,tmast.*,diff.*,'' empty1,'' empty2,'' empty3,'' empty4,'' empty5  from cp_question_masters mast ,cp_questions que,cp_sections sec,cp_question_categories cat,cp_question_type qtype, cp_testmaster tmast,cp_difficulty_level diff where mast.question_id in(53,69,60,259,285,286,287,305,307,308,311,312,313,314,320,327,330,331,345) and que.question_id=mast.question_id  and sec.section_id=mast.section_id and cat.category_id=mast.category_id and  qtype.question_type_id=mast.type_id and tmast.test_id=mast.test_id and  diff.diff_id=que.diff_id"
			// ;
			
			sql = sql.replace("[", "").replace("]", "");
			System.out.println("Resumet Test Questions Query is----------------------------------------------  : "+ sql);
			stmt1 = connection.createStatement();
  
			System.out.println();

			java.sql.ResultSet resultSet = st.executeQuery(sql);
			while (resultSet.next()) {
				QuestionsUploadBO bo = new QuestionsUploadBO();
				bo.setSection_id(resultSet.getInt("section_id"));
				bo.setQuestion_id(resultSet.getInt("question_id"));
				bo.setUser_id(bo1.getUserid());
				bo.setTest_no(bo1.getTest_no());
				bo.setCategory_id(resultSet.getInt("category_id"));
				bo.setTypeId(resultSet.getInt("type_id"));
				bo.setTest_id(resultSet.getInt("test_id"));
				String sublesson="select qle.sublesson_id  from cp_lessons le,cp_question_lessons qle where qle.sublesson_id=le.sublesson_id and qle.question_id="+bo1.getQuestion_id();
				stmt3=connection.createStatement(); 
				java.sql.ResultSet rest=stmt3.executeQuery(sublesson);
				while(rest.next())
				{
				    bo.setLesson_id(rest.getInt("sublesson_id"));
				} 
			
				bo.setPassageId(resultSet.getString("passage_id") != null ? resultSet.getInt("passage_id") : 0);
				if (bo.getPassageId() != 0) {
					String sql1 = "select passage.passage_title,passage.passage_type,passage.passage,passage.difficulty from cp_reading_comprehension_passage passage where passage.passage_id='"
							+ bo.getPassageId() + "'";
					resultSet1 = stmt1.executeQuery(sql1);
					while (resultSet1.next()) {
						bo.setPassageTitle(resultSet1
								.getString("passage_title"));
						bo.setPassage_type(resultSet1.getString("passage_type"));
						bo.setPassage(resultSet1.getString("passage"));
						bo.setPassage_difficultyId(resultSet1
								.getInt("difficulty"));
					}

				}
				bo.setGraphId(resultSet.getString("graph_id") != null ? resultSet
						.getInt("graph_id") : 0);
				if (bo.getGraphId() != 0) {
					String sql2 = "select graph.graph_title,graph.graph from cp_data_interpretation_graph graph where graph.graph_id='"
							+ bo.getGraphId() + "'";
					resultSet1 = stmt1.executeQuery(sql2);
					while (resultSet1.next()) {
						bo.setGraphTitle(resultSet1.getString("graph_title"));
						bo.setGraph(resultSet1.getString("graph"));
					}
				}
				bo.setQuestion_title(resultSet.getString("question_title"));
				bo.setQuestion_directions(resultSet
						.getString("question_directions"));
				bo.setSkillid1(resultSet.getInt("skill_id1"));
				bo.setSkillid2(resultSet.getInt("skill_id2"));
				bo.setSkillid3(resultSet.getInt("skill_id3"));
				bo.setDifficulty_id(resultSet.getInt("diff_id"));
				bo.setQuestion_no(resultSet.getString("question_no") != null ? resultSet
						.getInt("question_no") : 0);
				bo.setQuestion(resultSet.getString("question"));
				bo.setAvg_time(resultSet.getString("avg_time"));
				if (resultSet.getString("quantityA") != null) {
					bo.setQuantityA(resultSet.getString("quantityA"));
				}
				if (resultSet.getString("quantityB") != null) {
					bo.setQuantityB(resultSet.getString("quantityB"));
				}
				if (resultSet.getString("solution_text") != null) {
					bo.setSolution_text(resultSet.getString("solution_text"));
				}
				if (resultSet.getString("solution_video") != null) {
					bo.setSolution_video(resultSet.getString("solution_video"));
				}
				if (resultSet.getString("access_type") != null) {
					bo.setAccess_type(resultSet.getString("access_type"));
				}
				bo.setRefferal(resultSet.getString("referral"));
				bo.setStatus(resultSet.getString("status"));
				if (resultSet.getString("question_passage") != null) {
					bo.setQuestionPassage(resultSet
							.getString("question_passage"));
				}

				String sql4 = "select  answers.answer  from cp_question_masters qmas left join  cp_question_answers answers on qmas.question_id=answers.question_id where qmas.question_id like "+ bo.getQuestion_id();
				resultSet1 = stmt1.executeQuery(sql4);
				// System.out.println("Answers Query : "+sql4);
				ArrayList<String> al1 = new ArrayList<String>();
				while (resultSet1.next()) {
					if (resultSet1.getString(1) != null) {
						al1.add(resultSet1.getString(1));
					}
				}

				bo.setAnswers(al1);
				bo.setAnswers_length(bo.getAnswers().size());
				// System.out.println(bo.getAnswers_length()+"\t "+bo.getAnswers());
				String sql3 = "select choices.choice  from cp_question_masters qmas left join "
						+ " cp_question_choices choices on qmas.question_id=choices.question_id where qmas.question_id like'"
						+ bo.getQuestion_id() + "'";
				resultSet1 = stmt1.executeQuery(sql3);
				// System.out.println("Choices Quewry : "+sql3);
				ArrayList<String> al = new ArrayList<String>();
				while (resultSet1.next()) {
					if (resultSet1.getString("choice") != null) {
						al.add(resultSet1.getString("choice"));
					}
				}
				bo.setChoices(al);
				bo.setChoices_length(bo.getChoices().size());
				bo.setDifficulty_name(resultSet.getString("diff_name"));
				bo.setCategory_name(resultSet.getString("category_name"));
				String answersquery = "select tes.user_answer,tes.flagstatus,tes.guess,tes.user_time,tes.result from cp_test_results tes where tes.userid="+bo.getUser_id()+"  and  tes.test_no="+bo.getTest_no()+" and tes.question_id="+ bo.getQuestion_id() + "";
				resultSet2 = stmt2.executeQuery(answersquery);
				//System.out.println("The answersquery is--------------------------------"+answersquery);
				while (resultSet2.next())
				{   
     				String res=resultSet2.getString("result");										
					bo.setUserAnswer(resultSet2.getString("user_answer")!=null?resultSet2.getString("user_answer").toString().split(","):new String().split(","));
					bo.setIsAnswered(res.equalsIgnoreCase("CORRECT")||res.equalsIgnoreCase("INCORRECT")?"ANSWERED":"UNANSWERED");
					bo.setIsFlagged(resultSet2.getString("flagstatus")!=null?resultSet2.getString("flagstatus"):"UNFLAG");
					bo.setAnswerstatus(res);
					bo.setUser_time(resultSet2.getString("user_time"));
					//System.out.println("The list data is-----------------------"+res+"the answer sd"+bo.getUserAnswer()+"FLag status---------------"+bo.getIsFlagged());
				}	
				list.add(bo);
				//System.out.println("The list data is-----------------------"+list.size());
			}
		} catch (Exception e) {
           e.printStackTrace();
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
                e.printStackTrace();
			}
			catch (Exception e) {
				// TODO Auto-generated catch block
              e.printStackTrace();
			}
		}
		return list;

	}

	public FullLengthTestBO verifyTest(LoginUserBO bo) throws CPException {
		boolean flag = false;
		String result = "";
		Statement st = null;
		ResultSet rs = null;
		Connection connection = null;
		int sectionid;
		FullLengthTestBO bo1 = null;
		try {
			connection = DatabaseConnection.getCrunchPrepConnection();
			String query;
			st = connection.createStatement();
			query = "select t.flength_rowId,t.section_number,t.section_Id,t.pattern,t.section_status from cp_fulllength_test t where t.`status`='INCOMPLETE' and t.userId="
					+ bo.getUser_id();
			System.out.println(query);
			rs = st.executeQuery(query);
			if (rs.next()) {
				bo1 = new FullLengthTestBO();
				bo1.setFulllenth_rowId(rs.getInt("flength_rowId"));
				bo1.setSectionid(rs.getInt("section_number"));
				bo1.setSection_type(rs.getInt("section_Id"));
				bo1.setPattern(rs.getString("pattern"));
				bo1.setSection_status(rs.getString("section_status"));
				query = "select t.section_"+ (bo1.getSectionid())
						+ ",t.question_id,t.question_index,t.testNo,t.timeRemaining from cp_fulllength_test t where t.`status`='INCOMPLETE' and t.userId="
						+ bo.getUser_id() + " and t.section_number="
						+ bo1.getSectionid();
				System.out.println("verifyTest Query : " + query);
				rs = st.executeQuery(query);
				if (rs.next()) {
					bo1.setSection_questions(rs.getString(1));
					bo1.setQuestion_id(rs.getInt("question_id"));
					bo1.setQuestionIndex(rs.getInt("question_index"));
					bo1.setTest_no(rs.getInt("testNo"));
					bo1.setTimeRemaining(rs.getString("timeRemaining"));
				}
			}
		} catch (Exception exception) {
			// exception.printStackTrace();

		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block

			}
		}

		return bo1;
	}

	// ********************* RESULT PAGE *****************************

	public FullResultsBO resultPage(FullResultsBO fullResultsBO) {

		ResultSet resultSet = null, rs = null;
		ResultSet resultSet2 = null;
		String result_page = "  ";
		Statement st = null;
		Statement statement = null;
		Statement statement2 = null;
		Connection connection = null;

		// fullResultsBO. = new FullResultsBO();
		// Result set Data
		
		int question_no = 0;
		String result;
		String question_title;
		String lesson_name;
		String diff_name;
		String user_time;
		String avg_time;
		String flagstatus;
		String section_name;
		String category_name;
		String reason_id;
		int sectionid = 0;
		String user_answer = null;
		int type_id = 0;
		int ques_id = 0;

		// sum of ABG Times
		
		String avg_userpace = "00:00";
		String avg_otherspace = "00:00";
		String total_userpace = "00:00";
		String total_otherspace = "00:00";
		int avg_percentcorrect = 0;
		int test_no = 1;

		int correct = 0;
		int wrong = 0;
		String question_type_name;
		String guess;
		String test_date;
		String guessPercentage = "0";
		String correctPercentage = "0";
		String flaggedPercentage = "0";
		String guessIncorrectPercentage = "0";

		String stack_correct = "[0,0],";
		String stack_wrong = "[0,0],";
		String category_select="";

		System.out.println("_____________________________________________________  RESULT PAGE START _________________________________________________________");

		try {
 
			/*String query = "SELECT r.result,m.question_title,l.lesson_name,qt.question_type_name, d.diff_name,r.guess,r.flagstatus,DATE_FORMAT(r.test_date,'%b %d %Y %h:%i %p') as test_date, s.section_id,r.test_no,r.user_answer,m.type_id,r.question_id, "
					+ "r.user_time,q.avg_time,s.section_name,ct.category_name,r.reason FROM cp_test_results r,"
					+ "cp_question_masters m, cp_sections s,cp_lessons l,cp_difficulty_level d,cp_questions q,cp_question_type qt,cp_question_categories ct WHERE r.question_id= m.question_id AND r.section_id = s.section_id AND r.sublession_id = l.sublesson_id AND r.diff_id = d.diff_id  AND qt.question_type_id = r.type_id AND q.question_id = r.question_id AND ct.category_id = r.category_id AND r.userid="
					+ fullResultsBO.getUserid()
					+ " and r.test_no= "
					+ fullResultsBO.getTest_no() + "";*/
 
			
		 String query = "SELECT r.result,ct.category_name ,ifNull((select le.lesson_name  from cp_lessons le where le.sublesson_id=r.sublession_id )," +
					"'No-Lesson') as lesson_name,qt.question_type_name, d.diff_name,r.guess,r.flagstatus, DATE_FORMAT(r.test_date,'%d %b %Y')" +
					" AS test_date, s.section_id,r.test_no,r.user_answer,m.type_id,r.question_id, r.user_time,q.avg_time,s.section_name,  " +
					" ct.category_name,r.reason FROM cp_test_results r,cp_question_masters m, cp_sections s ,cp_difficulty_level d," +
					" cp_questions q,cp_question_type qt, cp_question_categories ct WHERE r.question_id= m.question_id AND r.section_id = s.section_id " +
					" AND r.diff_id = d.diff_id AND qt.question_type_id = r.type_id AND q.question_id = r.question_id AND" +
					" ct.category_id = r.category_id AND r.userid="+fullResultsBO.getUserid()+"  and r.test_no= "+ fullResultsBO.getTest_no()+" ORDER by r.row_id" ;
 
			
			//System.out.println("-->" + query);
			connection = DatabaseConnection.getCrunchPrepConnection();
			statement = connection.createStatement();

			System.out.println(" *** STEP1 : BIG QUERY : \n\t" + query);
			resultSet = statement.executeQuery(query);

			while (resultSet.next()) {

				question_no++;
				result = resultSet.getString("result");
				category_name = resultSet.getString("category_name");
				lesson_name = resultSet.getString("lesson_name");
				question_type_name = resultSet.getString("question_type_name");
				diff_name = resultSet.getString("diff_name");
				guess = resultSet.getString("guess");
				flagstatus = resultSet.getString("flagstatus");
				test_date = resultSet.getString("test_date");
				user_time = resultSet.getString("user_time");
				avg_time = resultSet.getString("avg_time");
				sectionid = resultSet.getInt("section_id");
				user_answer = resultSet.getString("user_answer");
				type_id = resultSet.getInt("type_id");
				test_no = resultSet.getInt("test_no");
				ques_id = resultSet.getInt("question_id");
				section_name = resultSet.getString("section_name");
				category_name = resultSet.getString("category_name");
				reason_id = resultSet.getString("reason");
				String op = "";
				
				if(!category_select.contains(category_name))
				{
					category_select = category_select+"\'"+category_name+"\', ";
					
					
					//[ 'Correct', 'Wrong']
				}
				
				if (result.equalsIgnoreCase("correct"))
				{
					correct++;
					op = op + "<option>Correct</option>";
				}	
				
				if (result.equalsIgnoreCase("incorrect"))
				{
					wrong++;
				// ********************
				// REASON***********************************
				ReviewReason val[] = ReviewReason.values();
				for (int i = 0; i < val.length; i++) {
					if (reason_id.equalsIgnoreCase(val[i].toString())) {
						op = op + "<option value=\'" + val[i]
								+ "\' selected=\'selected\'>" + val[i].reason
								+ "</option>";
					} else {

						op = op + "<option value=\'" + val[i] + "\'>"
								+ val[i].reason + "</option>";
					}
					}
				
				}   //reason(userid,qid,testid,reasonid)
				String onchage = "onchange=\'javascript:reason("+fullResultsBO.getUserid()+","+ques_id+","+test_no+",$(this).val());\'";
				String reason="<select class=\'form-control\' style=\'width:100%\' name=\'reason\'"+onchage+" >"+op+"</select>";
			//**************************** REASON END SATYA	 
                   String subQuery = "select DISTINCT IFNULL(ROUND(((select count(p.row_id) from cp_test_results p where  p.question_id="
						+ ques_id
						+ " and p.guess='GUESS')"
						+ "*100)/(select count(p.row_id) from cp_test_results p where  p.question_id="
						+ ques_id
						+ ")),0) as guessPercentage,"
						+ "IFNULL(ROUND(((select count(p.row_id) from cp_test_results p where p.question_id="
						+ ques_id
						+ " and  p.result='CORRECT')*100)/"
						+ "(select count(p.row_id) from cp_test_results p where p.question_id="
						+ ques_id
						+ ")),0) as correctPercentage,"
						+ "IFNULL(ROUND(((select count(p.row_id) from cp_test_results p where p.question_id="
						+ ques_id
						+ " and  p.flagstatus='FLAG')*100)/"
						+ "(select count(p.row_id) from cp_test_results p where p.question_id="
						+ ques_id
						+ ")),0) as flaggedPercentage,"
						+ "IFNULL(ROUND(((select count(p.row_id) from cp_test_results p where  p.question_id="
						+ ques_id
						+ " and p.guess='GUESS' and p.result='INCORRECT')"
						+ "*100)/(select count(p.row_id) from cp_test_results p where  p.question_id="
						+ ques_id
						+ ")),0) as guessIncorrectPercentage FROM cp_test_results";
				System.out.println("STEP 2 :   IN SUB QUERTY ******************\n\t\n"+ subQuery);
 
				st = connection.createStatement();
				rs = st.executeQuery(subQuery);

				if (rs.next()) {
					guessPercentage = rs.getString("guessPercentage");
					correctPercentage = rs.getString("correctPercentage");
					flaggedPercentage = rs.getString("flaggedPercentage");
					guessIncorrectPercentage = rs
							.getString("guessIncorrectPercentage");
				}

				System.out.println("UserTime : " + user_answer);
				System.out.println("question : " + ques_id);
				System.out.println("type : " + type_id);

				String hisdiv = "";

				String qry = "(SELECT test.result, TIME_FORMAT(test.user_time,'%i:%S') user_time, test.test_date test_date, test.question_id,test.guess 	FROM cp_test_results test  	WHERE test.question_id="
						+ ques_id
						+ " and test.userid = "
						+ fullResultsBO.getUserid()
						+ "  order by test.test_date desc limit 5) 		UNION ALL 		(SELECT qh.question_status, TIME_FORMAT(qh.question_time,'%i:%S') user_time,qh.question_date test_date ,qh.question_id,qh.question_guess 	FROM cp_question_history qh where qh.user_id= "
						+ fullResultsBO.getUserid()
						+ "  and qh.question_id="
						+ ques_id
						+ "  order by qh.question_date desc limit 5 ) order by   test_date  limit 5";

				statement2 = connection.createStatement();
				resultSet2 = statement2.executeQuery(qry);

				while (resultSet2.next()) {
					String res = resultSet2.getString("result");
					String usertime = resultSet2.getString("user_time");
					String tdate = resultSet2.getString("test_date");
					String guessdata = resultSet2.getString("guess");
					SimpleDateFormat mdyFormat = new SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss");
					SimpleDateFormat dmyFormat = new SimpleDateFormat(
							"MMM dd yyy hh:mm a");

					tdate = dmyFormat.format(mdyFormat.parse(tdate));

					// String s=MonthFORMAT1.format(MonthFORMAT1.parse(tdate));
					if (res.equalsIgnoreCase("correct")) {
						result = "Correct";

						hisdiv = hisdiv
								+ "<span class=@font-15@><i class=@fa fa-check-circle text-success@></i><span class=@p-l-10 p-r-10@>Correct</span><i class=@fa fa-arrow-right@></i> <i class=@fa fa-clock-circle@></i> <span class=@p-l-10 p-r-10@>"
								+ usertime + " (" + tdate + ")  " + guessdata
								+ "</span></span><br>";

					} else {
						result = "Wrong";

						
						
						hisdiv = hisdiv
								+ "<span class=@font-15@><i class=@fa fa-times-circle text-danger@></i><span class=@p-l-10 p-r-10 text-danger@> Wrong</span><i class=@fa fa-arrow-right@></i> <i class=@fa fa-clock-circle@></i> <span class=@p-l-10 p-r-10@>"
								+ usertime + " (" + tdate + ")  " + guessdata
								+ "</span></span><br>";
					}

				}// while end

				// System.out.println("******  QUERY ***********HIS DIV********\n\t"+hisdiv);
				// System.out.println("Result : "+result);

				result_page = result_page
						+ "{"
						+ "	\"result\": \""
						+ result
						+ "\","
						+ "   \"quesTitle\": \""
						+ category_name
						+ "\","
						+ "   \"topic\": \""
						+ lesson_name
						+ "\","
						+ "    \"type\": \""
						+ question_type_name
						+ "\","
						+ "     \"difficulty\": \""
						+ diff_name
						+ "\",	"
						+ "      \"guessed\": \""
						+ guess
						+ "\","
						+ "  \"flag\": \""
						+ flagstatus
						+ "\","
						+ "  \"date\": \""
						+ test_date
						+ "\","
						+ " \"retry\": \"<a href='javascript:getFullSessionQuestion("+ ques_id+ ","	+ type_id+ ","	+ sectionid
						+ ","
						+ test_no
						+ ");' class=\'btn btn-default\'><i class=\'fa fa-refresh \'></i>&nbsp;Retry</a>\",	"
						+ "  \"percentCorrect\":\"" + correctPercentage + "\","
						+ "  \"flaggedPercentage\": \"" + flaggedPercentage
						+ "\"," + "  \"user_time\": \"" + user_time + "\","
						+ "  \"avg_time\": \"" + avg_time + "\","
						+ "  \"section_name\": \"" + section_name + "\","
						+ "  \"category_name\": \"" + category_name + "\","
						+ "  \"guessPercentage\": \"" + guessPercentage + "\","
						+ "  \"guessIncorrectPercentage\": \""
						+ guessIncorrectPercentage + "\"," + "  \"hisdiv\": \""
						+ hisdiv + "\"," + "  \"reason\": \"" + reason + "\""
						+ "    },";

			}// while end

			String qry = "select SEC_TO_TIME((SUM(TIME_TO_SEC(r.user_time)))/ COUNT(*)) as avg_userpace, SEC_TO_TIME((SUM(TIME_TO_SEC(q.avg_time)))/ COUNT(*)) as avg_otherspace,SEC_TO_TIME((SUM(TIME_TO_SEC(r.user_time)))) as total_userpace, SEC_TO_TIME((SUM(TIME_TO_SEC(q.avg_time)))) as total_otherspace from cp_test_results r,cp_question_masters m, cp_sections s,cp_lessons l,cp_difficulty_level d,cp_questions q  where r.question_id= m.question_id and r.section_id = s.section_id and r.sublession_id = l.sublesson_id and r.diff_id = d.diff_id and q.question_id = r.question_id and r.userid="
					+ fullResultsBO.getUserid()
					+ " and r.test_no= "
					+ fullResultsBO.getTest_no() + "";
			// System.out.println("  **  STEP4 : AverageQuery:"+qry);
			resultSet = statement.executeQuery(qry);
			if (resultSet.next()) {
				avg_userpace = resultSet.getString("avg_userpace");
				avg_otherspace = resultSet.getString("avg_otherspace");
				total_userpace = resultSet.getString("total_userpace");
				total_otherspace = resultSet.getString("total_otherspace");
			}

			// System.out.println("******************************  \n  Correct: "+correct+"Question No : "+question_no+"Wrong :"+wrong);

			if (question_no > 0) {
				avg_percentcorrect = (correct * 100 / question_no);
			}

			fullResultsBO.setTotal_qsns(question_no);
			fullResultsBO.setCorrect(correct);
			fullResultsBO.setWrong(wrong);
			fullResultsBO.setAvg_userpace(avg_userpace);
			fullResultsBO.setAvg_otherspace(avg_otherspace);
			fullResultsBO.setTotal_userpace(total_userpace);
			fullResultsBO.setTotal_otherspace(total_otherspace);
			fullResultsBO.setAvg_percentcorrect(avg_percentcorrect);
			fullResultsBO.setResult_page(result_page);
			fullResultsBO.setSection_id(sectionid);
			fullResultsBO.setTest_no(test_no);
			fullResultsBO.setUser_answer(user_answer);
			fullResultsBO.setType_id(type_id);
			fullResultsBO.setStack_correct(stack_correct);
			fullResultsBO.setStack_wrong(stack_wrong);
			
			
			int len = (category_select.length()-2);
			category_select = category_select.substring(0, len);
			System.out.println("Cata----->   SELECT ------>   "+category_select+" Length-->"+len);			
			fullResultsBO.setCategory_select(category_select);
			
			System.out.println("_____________________________________________________  RESULT PAGE END  _________________________________________________________");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection(resultSet, statement, connection);
			try {
				if (resultSet2 != null)
					resultSet2.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return fullResultsBO;
	}

	public FullResultsBO updateReason(FullResultsBO fullResultsBO) {

		Statement st = null;
		Connection con = null;
		try {

			con = DatabaseConnection.getCrunchPrepConnection();
			st = con.createStatement();
			String qry = "UPDATE `cp_test_results` r SET r.reason='"
					+ fullResultsBO.getReason() + "' WHERE  r.userid= "
					+ fullResultsBO.getUserid() + " and r.question_id = "
					+ fullResultsBO.getQuestion_id() + " and r.test_no = "
					+ fullResultsBO.getTest_no();
			System.out.println("UPDATE QRY------------> " + qry);
			int k = st.executeUpdate(qry);
			if (k > 0) {
				System.out.println("Reason Updated Successfully");

			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				con.close();
				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return resultPage(fullResultsBO);
	}

	/**
	 * @author vgrandhi
	 * @param json
	 * @return
	 * @throws ConnectionException
	 * @throws SQLException
	 * @throws JSONException
	 */
	public JSONObject overallPerformanceData(JSONObject json)
			throws ConnectionException, SQLException, JSONException {

		JSONObject resultJson = new JSONObject();

		ResultSet rs = null;
		Statement st = null;
		Connection con = null;
		con = DatabaseConnection.getCrunchPrepConnection();
		st = con.createStatement();

		int correctCount = 0, incorrectCount = 0,guessedcount=0,flagcount=0;
		try{
		/*
		 * 
		 * String correctIncorrectQuery =
		 * "SELECT test.result,test.question_id,test.userid,max(test.test_date),test.test_master_id "
		 * +
		 * "FROM cp_test_results test   where test.userid ='"+json.get("userId"
		 * ).
		 * toString()+"' group by test.question_id   order by test.question_id "
		 * ;
		 */

		String correctIncorrectQuery = "(select * from(select * from ((SELECT test.row_id rowidd,test.result result,test.question_id questionid,test.userid userid,test.test_date testdate,test.test_master_id,test.user_time usertime,test.section_id sectionid,test.guess,test.flagstatus 	FROM cp_test_results test   where test.userid ='"
				+ json.get("userId").toString()
				+ "'  order by test.question_id ) 	union 	(select qh.question_history_id rowidd,qh.question_status result ,qh.question_id questionid,qh.user_id  userid ,qh.question_date testdate,'' ,qh.question_time usertime,qh.section_id sectionid ,qh.question_guess,''   from cp_question_history qh where qh.user_id='"
				+ json.get("userId").toString()
				+ "' ORDER BY qh.question_id)) as a order by questionid ,testdate desc ) ad group by questionid )";
		System.out.println("CI Qry : "+correctIncorrectQuery);
		rs = st.executeQuery(correctIncorrectQuery);
		while (rs.next()) {

			if (rs.getString("result").equalsIgnoreCase("CORRECT"))
				correctCount++;
			else 
				incorrectCount++;
			
			if (rs.getString("guess")!=null &&rs.getString("guess").equalsIgnoreCase("GUESS"))
				guessedcount++;
 		if(rs.getString("flagstatus")!=null && rs.getString("flagstatus").equalsIgnoreCase("FLAG"))

				flagcount++;
		}

	//	String otherResults = "(SELECT  SEC_TO_TIME(SUM(TIME_TO_SEC( qh.question_time))) TotalTime,qh.user_id,(SELECT IFNULL( SEC_TO_TIME(SUM(TIME_TO_SEC( qh.question_time))),'00:00:00') FROM cp_question_history qh  where qh.user_id ="+ json.get("userId").toString()+"  and qh.section_id=1) QuantTotaltime, (SELECT  IFNULL(SEC_TO_TIME(SUM(TIME_TO_SEC( qh.question_time))),'00:00:00') FROM cp_question_history qh  where qh.user_id ="+ json.get("userId").toString()+"  and qh.section_id=2) VerbalTotaltime, (SELECT  IFNULL(SEC_TO_TIME(AVG( TIME_TO_SEC(qh.question_time))),'00:00:00')  FROM cp_question_history qh  where qh.user_id ="+ json.get("userId").toString()+"  and qh.question_status='CORRECT'  and qh.section_id=1 ) QuantAvgTime, (SELECT  IFNULL(SEC_TO_TIME(AVG( TIME_TO_SEC(qh.question_time))),'00:00:00')  FROM cp_question_history qh  where qh.user_id ="+ json.get("userId").toString()+"   and qh.section_id=2) VerbalAvgTime, (SELECT  IFNULL(SEC_TO_TIME(AVG( TIME_TO_SEC(qh.question_time))),'00:00:00')  FROM cp_question_history qh  where qh.user_id ="+ json.get("userId").toString()+"   and qh.section_id=1 and qh.question_status='CORRECT') QuantCorrectTime, (SELECT  IFNULL(SEC_TO_TIME(AVG( TIME_TO_SEC(qh.question_time))) ,'00:00:00') FROM cp_question_history qh  where qh.user_id ="+ json.get("userId").toString()+"   and qh.section_id=1 and qh.question_status='INCORRECT') QuantInCorrectTime, (SELECT  IFNULL(SEC_TO_TIME(AVG( TIME_TO_SEC(qh.question_time))) ,'00:00:00') FROM cp_question_history qh  where qh.user_id ="+ json.get("userId").toString()+"   and qh.section_id=2 and qh.question_status='CORRECT') VerbalCorrectTime, (SELECT  IFNULL(SEC_TO_TIME(AVG( TIME_TO_SEC(qh.question_time))) ,'00:00:00') FROM cp_question_history qh  where qh.user_id ="+ json.get("userId").toString()+"   and qh.section_id=2 and qh.question_status='INCORRECT') VerbalInCorrectTime,(SELECT  count(qh.question_guess) Guessed FROM cp_question_history qh  where qh.user_id="+ json.get("userId").toString()+" and qh.question_guess='GUESS'   group by qh.question_guess )Guessed,('0')Flagged FROM cp_question_history qh  where qh.user_id ="+ json.get("userId").toString()+") union ((SELECT  IFNULL(SEC_TO_TIME(SUM(TIME_TO_SEC( test.user_time))),'00:00:00') TotalTime,test.userid,(SELECT  IFNULL(SEC_TO_TIME(SUM(TIME_TO_SEC( test.user_time))),'00:00:00') FROM cp_test_results test  where test.userid="+ json.get("userId").toString()+"  and test.section_id=1) QuantTotaltime,(SELECT  IFNULL(SEC_TO_TIME(SUM(TIME_TO_SEC( test.user_time))),'00:00:00')  FROM cp_test_results test  where test.userid="+ json.get("userId").toString()+"  and test.section_id=2) VerbalTotaltime,(SELECT  IFNULL(SEC_TO_TIME(AVG( TIME_TO_SEC( test.user_time))),'00:00:00') FROM cp_test_results test  where test.userid="+ json.get("userId").toString()+"  and test.section_id=1) QuantAvgTime,(SELECT  IFNULL(SEC_TO_TIME(AVG( TIME_TO_SEC( test.user_time))),'00:00:00') FROM cp_test_results test  where test.userid="+ json.get("userId").toString()+"  and test.section_id=2) VerbalAvgTime,(SELECT IFNULL( SEC_TO_TIME(AVG( TIME_TO_SEC( test.user_time))) ,'00:00:00') FROM cp_test_results test  where test.userid="+ json.get("userId").toString()+"  and test.section_id=1 and test.result='CORRECT') QuantCorrectTime,(SELECT  IFNULL(SEC_TO_TIME(AVG( TIME_TO_SEC( test.user_time))),'00:00:00') FROM cp_test_results test  where test.userid="+ json.get("userId").toString()+"  and test.section_id=1 and test.result='INCORRECT') QuantINCorrectTime,(SELECT  IFNULL(SEC_TO_TIME(AVG( TIME_TO_SEC( test.user_time))),'00:00:00') FROM cp_test_results test  where test.userid="+ json.get("userId").toString()+"  and test.section_id=2 and test.result='CORRECT') VerbalCorrectTime,(SELECT  IFNULL(SEC_TO_TIME(AVG( TIME_TO_SEC( test.user_time))),'00:00:00') FROM cp_test_results test  where test.userid="+ json.get("userId").toString()+"  and test.section_id=2 and test.result='INCORRECT') VerbalInCorrectTime,(SELECT  count(test.guess) Guessed FROM cp_test_results test  where test.userid="+ json.get("userId").toString()+" and test.guess='GUESS'   group by test.guess )Guessed,(SELECT  count(test.flagstatus) Flagged FROM cp_test_results test  where test.userid="+ json.get("userId").toString()+" and test.flagstatus='FlAG'  group by test.flagstatus )Flagged  FROM cp_test_results test  where test.userid="+ json.get("userId").toString()+"))";
		
		String otherResults = " select SEC_TO_TIME(SUM(TIME_TO_SEC(sub.TotalTime))) TotalTime ,SEC_TO_TIME(SUM(TIME_TO_SEC(sub.QuantCorrectTime))) QuantAvgCorrectTime," +
				"SEC_TO_TIME(SUM(TIME_TO_SEC(sub.QuantInCorrectTime))) QuantAvgInCorrectTime,SEC_TO_TIME(SUM(TIME_TO_SEC(sub.VerbalCorrectTime))) VerbalAvgCorrectTime," +
				"SEC_TO_TIME(SUM(TIME_TO_SEC(sub.VerbalInCorrectTime))) VerbalAvgInCorrectTime from(( " +
				"SELECT  SEC_TO_TIME(SUM(TIME_TO_SEC( qh.question_time))) TotalTime,qh.user_id,(SELECT IFNULL( SEC_TO_TIME(SUM(TIME_TO_SEC( qh.question_time))),'00:00:00') FROM cp_question_history qh  " +
				"where qh.user_id ="+ json.get("userId").toString()+"  and qh.section_id=1) QuantTotaltime, (SELECT  IFNULL(SEC_TO_TIME(SUM(TIME_TO_SEC( qh.question_time))),'00:00:00') FROM " +
						"cp_question_history qh  where qh.user_id ="+ json.get("userId").toString()+"  and qh.section_id=2) VerbalTotaltime, " +
								"(SELECT  IFNULL(SEC_TO_TIME(AVG( TIME_TO_SEC(qh.question_time))),'00:00:00')  FROM cp_question_history qh  where qh.user_id ="+ json.get("userId").toString()+"  and " +
										"qh.question_status='CORRECT'  and qh.section_id=1 ) QuantAvgTime, (SELECT  IFNULL(SEC_TO_TIME(AVG( TIME_TO_SEC(qh.question_time))),'00:00:00')  FROM " +
										"cp_question_history qh  where qh.user_id ="+ json.get("userId").toString()+"   and qh.section_id=2) VerbalAvgTime, " +
												"(SELECT  IFNULL(SEC_TO_TIME(AVG( TIME_TO_SEC(qh.question_time))),'00:00:00')  FROM cp_question_history qh  " +
												"where qh.user_id ="+ json.get("userId").toString()+"   and qh.section_id=1 and qh.question_status='CORRECT') QuantCorrectTime, " +
														"(SELECT  IFNULL(SEC_TO_TIME(AVG( TIME_TO_SEC(qh.question_time))) ,'00:00:00') FROM cp_question_history qh  " +
														"where qh.user_id ="+ json.get("userId").toString()+"   and qh.section_id=1 and qh.question_status='INCORRECT') QuantInCorrectTime, " +
																"(SELECT  IFNULL(SEC_TO_TIME(AVG( TIME_TO_SEC(qh.question_time))) ,'00:00:00') FROM cp_question_history qh  " +
																"where qh.user_id ="+ json.get("userId").toString()+"   and qh.section_id=2 and qh.question_status='CORRECT') VerbalCorrectTime, " +
																		"(SELECT  IFNULL(SEC_TO_TIME(AVG( TIME_TO_SEC(qh.question_time))) ,'00:00:00') FROM cp_question_history qh  " +
																		"where qh.user_id ="+ json.get("userId").toString()+"   and qh.section_id=2 and qh.question_status='INCORRECT') " +
																				"VerbalInCorrectTime,(SELECT  count(qh.question_guess) Guessed FROM cp_question_history qh  " +
																				"where qh.user_id="+ json.get("userId").toString()+" and qh.question_guess='GUESS'   group by qh.question_guess )Guessed," +
																						"('0')Flagged FROM cp_question_history qh  where qh.user_id ="+ json.get("userId").toString()+") union " +
																								"((SELECT  IFNULL(SEC_TO_TIME(SUM(TIME_TO_SEC( test.user_time))),'00:00:00') TotalTime,test.userid," +
																								"(SELECT  IFNULL(SEC_TO_TIME(SUM(TIME_TO_SEC( test.user_time))),'00:00:00') FROM cp_test_results test " +
																								" where test.userid="+ json.get("userId").toString()+"  and test.section_id=1) QuantTotaltime," +
																										"(SELECT  IFNULL(SEC_TO_TIME(SUM(TIME_TO_SEC( test.user_time))),'00:00:00')  " +
		"FROM cp_test_results test  where test.userid="+ json.get("userId").toString()+"  and test.section_id=2) VerbalTotaltime," +
		"(SELECT  IFNULL(SEC_TO_TIME(AVG( TIME_TO_SEC( test.user_time))),'00:00:00') FROM cp_test_results test  where test.userid="+ json.get("userId").toString()+"  and test.section_id=1) QuantAvgTime," +
		"(SELECT  IFNULL(SEC_TO_TIME(AVG( TIME_TO_SEC( test.user_time))),'00:00:00') FROM cp_test_results test  where test.userid="+ json.get("userId").toString()+"  and test.section_id=2) VerbalAvgTime," +
		"(SELECT IFNULL( SEC_TO_TIME(AVG( TIME_TO_SEC( test.user_time))) ,'00:00:00') FROM cp_test_results test  where test.userid="+ json.get("userId").toString()+"  and test.section_id=1 and " +
		"test.result='CORRECT') QuantCorrectTime,(SELECT  IFNULL(SEC_TO_TIME(AVG( TIME_TO_SEC( test.user_time))),'00:00:00') FROM cp_test_results test  where test.userid="+ json.get("userId").toString()+"  and test.section_id=1 and test.result='INCORRECT') QuantINCorrectTime,(SELECT  IFNULL(SEC_TO_TIME(AVG( TIME_TO_SEC( test.user_time))),'00:00:00') FROM cp_test_results test  where test.userid="+ json.get("userId").toString()+"  and test.section_id=2 and test.result='CORRECT') VerbalCorrectTime,(SELECT  IFNULL(SEC_TO_TIME(AVG( TIME_TO_SEC( test.user_time))),'00:00:00') FROM cp_test_results test  where test.userid="+ json.get("userId").toString()+"  and test.section_id=2 and test.result='INCORRECT') VerbalInCorrectTime,(SELECT  count(test.guess) Guessed FROM cp_test_results test  where test.userid="+ json.get("userId").toString()+" and test.guess='GUESS'   group by test.guess )Guessed,(SELECT  count(test.flagstatus) Flagged FROM cp_test_results test  where test.userid="+ json.get("userId").toString()+" and test.flagstatus='FlAG'  group by test.flagstatus )Flagged  FROM cp_test_results test  where test.userid="+ json.get("userId").toString()+"))) sub";
		System.out.println("The Query is-------------------------------------"+otherResults);
		
		rs = st.executeQuery(otherResults);

		
		
		if(rs.next()) {
			
			
							
			
			resultJson.put("others", rs.getString("TotalTime")+"@"+rs.getString("QuantAvgCorrectTime")+"@"+rs.getString("QuantAvgInCorrectTime")+"@"+rs.getString("VerbalAvgCorrectTime")+"@"+rs.getString("VerbalAvgInCorrectTime"));

		}
		System.out.println("Guess "+guessedcount+" "+flagcount);
		
		resultJson.put("pie-chart-data", correctCount + "@" + incorrectCount+"@"+guessedcount+"@"+flagcount);
		DBUtil.closeConnection(rs, st, con);
		
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
		
		
		return resultJson;
	}
	
	
	public ArrayList<OverallPerformanceBO> performanceSummary(JSONObject json)
			throws ConnectionException, SQLException, JSONException {

		ArrayList<OverallPerformanceBO> performanceSummary = new ArrayList<OverallPerformanceBO>();
		ResultSet rs = null;
		Statement st = null;
		Connection con = null;
		con = DatabaseConnection.getCrunchPrepConnection();
		st = con.createStatement();
		String performanceSummaryQuery = "select testres.test_master_id,testres.test_date,testcr.session_name,testres.userid,testres.test_no ,"
				+ "(select count(*) from cp_test_results tes where tes.result='CORRECT' and tes.test_no=testres.test_no) TestCorrectcount ,"
				+ "(select count(*) from cp_test_results tes where tes.result='INCORRECT' and tes.test_no=testres.test_no) TestINCorrectcount,"
				+ "(select count(*) from cp_test_results tes where  tes.test_no=testres.test_no) Totalcount,"
				+ "((select count(*) from cp_test_results tes where tes.result='CORRECT' and tes.test_no=testres.test_no)*100/(select count(*) from cp_test_results tes where  tes.test_no=testres.test_no))Correctper,"
				+ "((select count(*) from cp_test_results tes where tes.result='INCORRECT' and tes.test_no=testres.test_no)*100/(select count(*) from cp_test_results tes where  tes.test_no=testres.test_no))InCorrectper,"
				+ "(select count(*) from cp_test_results tes where tes.result='CORRECT' and tes.test_no=testres.test_no and tes.section_id=1) CorrectcountQuant,"
				+ "(select count(*) from cp_test_results tes where tes.result='CORRECT' and tes.test_no=testres.test_no and tes.section_id=2) CorrectcountVerbal,"
				+ "(select count(*) from cp_test_results tes where tes.result='INCORRECT' and tes.test_no=testres.test_no and tes.section_id=1) INCorrectcountQuant,"
				+ "(select count(*) from cp_test_results tes where tes.result='INCORRECT' and tes.test_no=testres.test_no and tes.section_id=2) INCorrectcountVerbal,"
				+ "(select count(*) from cp_test_results tes where  tes.test_no=testres.test_no and tes.section_id=1) QuantCount,"
				+ "(select count(*) from cp_test_results tes where  tes.test_no=testres.test_no and tes.section_id=2) VerbalCount ,"
				+"(SELECT ts.QuantRawScore	FROM cp_test_score ts	WHERE ts.test_no=testres.test_no and ts.user_id="+json.get("userId").toString()+")QantRawScore,"
				+"(SELECT ts.VerbalRawScore	FROM cp_test_score ts	WHERE ts.test_no=testres.test_no  and ts.user_id="+json.get("userId").toString()+")VerbalRawscore "
				+ "from cp_test_results testres,cp_testcreation testcr where testres.test_no=testcr.test_no and testres.test_master_id=testcr.testmaster_id and testres.userid='"
				+ json.get("userId").toString()
				+ "' group by testres.test_no order by testres.test_master_id";
		
		System.out.println("performance summary : "+performanceSummaryQuery);
		rs = st.executeQuery(performanceSummaryQuery);

		SimpleDateFormat mdyFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    SimpleDateFormat dmyFormat = new SimpleDateFormat("MMM dd yyy hh:mm a");
	    DecimalFormat df=new DecimalFormat("#.##");
		while(rs.next()){
	

			OverallPerformanceBO bean = new OverallPerformanceBO();
			bean.setTestno(rs.getString("test_no"));
			bean.setTestName(rs.getString("session_name"));
			try {
				
				bean.setTestDate(dmyFormat.format(mdyFormat.parse(rs.getString("test_date"))));
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			bean.setTestId(rs.getShort("test_master_id"));
		//	bean.setQuantScore(rs.getShort("QuantCount"));
		//	bean.setVerbalScore(rs.getShort("VerbalCount"));
			bean.setQuantScore(rs.getShort("QantRawScore"));
				bean.setVerbalScore(rs.getShort("VerbalRawscore"));
			bean.setCorrectPercentage(Double.parseDouble(df.format(rs.getDouble("Correctper"))));
			bean.setIncorrectPercentage(Double.parseDouble(df.format(rs.getDouble("InCorrectper"))));
			performanceSummary.add(bean);

		}

		DBUtil.closeConnection(rs, st, con);
		return performanceSummary;

	}

	
		
	
	public JSONObject QuestionAnalysisData(JSONObject json)
			throws ConnectionException, SQLException, JSONException {

		JSONObject resultJson = new JSONObject();

		ResultSet rs = null,resultSet=null,questionHistoryres=null;
		Statement st = null,st1 = null,questionHistory=null;
		Connection con = null;
		con = DatabaseConnection.getCrunchPrepConnection();
		st = con.createStatement();
		st1 = con.createStatement();
		JSONObject questioArrayList=null;
		JSONArray questioArrayList1=new JSONArray();
		int correctCount = 0, incorrectCount = 0,TotalSolvedCount=0,TotalPoolQuestion=0;
		try
		{

		String correctIncorrectQuery = "(select ad.*,qc.category_name,qd.diff_name,SEC_TO_TIME(avg(qm.user_time)) avgtime from(select * from ((SELECT test.row_id rowidd,test.result result,test.question_id questionid,test.userid userid,test.test_date testdate,test.test_master_id,test.user_time usertime,test.section_id sectionid,test.guess,test.flagstatus,test.reason,maste.question_title,maste.type_id,test.test_no 	FROM cp_test_results test,cp_question_masters maste   where maste.question_id = test.question_id and test.userid ='"
				+ json.get("userId").toString()
				+ "'  order by test.question_id ) 	union 	(select qh.question_history_id rowidd,qh.question_status result ,qh.question_id questionid,qh.user_id  userid ,qh.question_date testdate,qh.test_master_id ,qh.question_time usertime,qh.section_id sectionid ,qh.question_guess,'','',maste.question_title,maste.type_id,qh.testno  from cp_question_history qh,cp_question_masters maste where maste.question_id = qh.question_id and qh.user_id='"
				+ json.get("userId").toString()
				+ "' ORDER BY qh.question_id)) as a order by questionid ,testdate desc ) ad,cp_test_results qm,cp_question_categories qc ,cp_difficulty_level qd where ad.questionid=qm.question_id and  qm.category_id=qc.category_id and qm.diff_id=qd.diff_id  group by questionid )  union (select count(*) ,'','','','','','','','','','','','','','','','' from cp_question_masters)";
		
		System.out.println("The Query in qa------------------------"+correctIncorrectQuery);
		SimpleDateFormat mdyFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    SimpleDateFormat dmyFormat = new SimpleDateFormat("MMM dd yyy");
	    JSONObject analysisBO=null;
		rs = st.executeQuery(correctIncorrectQuery);
		int i=1;
		while (rs.next()) {
		
			i++;
			if(rs.isLast())
			{
				//System.out.println("In last result set----------------------------");	
				TotalPoolQuestion=rs.getInt("rowidd");
			}
			else{
			    analysisBO=new JSONObject();
			    String s=rs.getString("result");
			   String flagstatus=rs.getString("flagstatus");
			    short questionid=rs.getShort("questionid");
			   	analysisBO.put("Result",(s.equalsIgnoreCase("INCORRECT"))?"WRONG":s);
				analysisBO.put("Questionid",questionid);
				analysisBO.put("Sectionid",rs.getShort("sectionid"));
				analysisBO.put("Flag",flagstatus!=null?flagstatus.equalsIgnoreCase("FLAG")?"FLAGGED":"UNFLAG":"");
				analysisBO.put("Guess",rs.getString("guess"));
				analysisBO.put("User_time",rs.getString("usertime"));
				analysisBO.put("Reason",ReviewReason.getMyReason(rs.getString("reason")));
				analysisBO.put("QuestionTitle",rs.getString("question_title"));
				analysisBO.put("TestDate",dmyFormat.format(mdyFormat.parse(rs.getString("testdate"))));
				analysisBO.put("TypeId",rs.getString("type_id"));
				analysisBO.put("TestNo",rs.getString("test_no"));
				analysisBO.put("TestMasterId",rs.getString("test_master_id"));
				analysisBO.put("CategoryName", rs.getString("category_name"));
				analysisBO.put("Difficulty", rs.getString("diff_name"));
				analysisBO.put("AvgTime", rs.getString("avgtime"));				
			    if(analysisBO.getString("TestMasterId").equalsIgnoreCase("1")) analysisBO.put("RetryButton","<a class='btn btn-success' href=Get-Full-Session-Retry-Questions.action?ques_id="+analysisBO.get("Questionid")+"&type_id="+analysisBO.get("TypeId")+"&retrytestno="+analysisBO.get("TestNo")+"&sectionnumber="+analysisBO.get("Sectionid")+">Retry</a>");
				else if(analysisBO.getString("TestMasterId").equalsIgnoreCase("2")) analysisBO.put("RetryButton","<a class='btn btn-success' href=Get-Full-Session-Retry-Questions.action?ques_id="+analysisBO.get("Questionid")+"&type_id="+analysisBO.get("TypeId")+"&retrytestno="+analysisBO.get("TestNo")+"&sectionnumber="+analysisBO.get("Sectionid")+">Retry</a>");
				if (s.equalsIgnoreCase("CORRECT"))
				correctCount++;
				if (s.equalsIgnoreCase("INCORRECT")||s.equalsIgnoreCase("SKIPPED"))
				incorrectCount++;
				String subQuery="select DISTINCT IFNULL(ROUND(((select count(p.row_id) from cp_test_results p where  p.question_id="+questionid+" and p.guess='GUESS')" +
						"*100)/(select count(p.row_id) from cp_test_results p where  p.question_id="+questionid+")),0) as guessPercentage," +
						"IFNULL(ROUND(((select count(p.row_id) from cp_test_results p where p.question_id="+questionid+" and  p.result='CORRECT')*100)/" +
						"(select count(p.row_id) from cp_test_results p where p.question_id="+questionid+")),0) as correctPercentage," +
						"IFNULL(ROUND(((select count(p.row_id) from cp_test_results p where p.question_id="+questionid+" and  p.flagstatus='FLAG')*100)/" +
						"(select count(p.row_id) from cp_test_results p where p.question_id="+questionid+")),0) as flaggedPercentage," +
						"IFNULL(ROUND(((select count(p.row_id) from cp_test_results p where  p.question_id="+questionid+" and p.guess='GUESS' and p.result='INCORRECT')" +
						"*100)/(select count(p.row_id) from cp_test_results p where  p.question_id="+questionid+")),0) as guessIncorrectPercentage FROM cp_test_results";
				
			//	System.out.println("STEP 2 :   IN SUB QUERTY ******************\n\t\n"+subQuery);
				
				resultSet=st1.executeQuery(subQuery);
				
				while(resultSet.next()){
					
					 analysisBO.put("GuessPercentage",resultSet.getString("guessPercentage")); 			
				     analysisBO.put("CorrectPercentage", resultSet.getString("correctPercentage")); 			
				     analysisBO.put("FlaggedPercentage", resultSet.getString("flaggedPercentage")); 			
				     analysisBO.put("GuessInCorrectPercentage", resultSet.getString("guessIncorrectPercentage")); 
				}
				
				
				
				String hisdiv = "";
				
				//String qry = "select test.result,TIME_FORMAT(test.user_time,'%i:%S') user_time,DATE_FORMAT(test.test_date,'%b %d %Y %h:%i %p') test_date, test.question_id from cp_test_results test where test.question_id="+ques_id+" and test.userid = "+resultsBO.getUserid()+"  order by test.test_date limit 5 "    ;
			    //String qry="(SELECT test.result, TIME_FORMAT(test.user_time,'%i:%S') user_time, DATE_FORMAT(test.test_date,'%b %d %Y %h:%i %p') test_date, test.question_id,test.guess 	FROM cp_test_results test  	WHERE test.question_id="+ques_id+" and test.userid = "+resultsBO.getUserid()+"  order by test.test_date limit 5) 		UNION ALL 		(SELECT qh.question_status, TIME_FORMAT(qh.question_time,'%i:%S') user_time,DATE_FORMAT(qh.question_date,'%b %d %Y %h:%i %p'),qh.question_id,qh.question_guess 	FROM cp_question_history qh where qh.user_id= "+resultsBO.getUserid()+"  and qh.question_id="+ques_id+"  order by qh.question_date limit 5 ) order by   test_date  limit 5";
			   
			   String qry="(SELECT test.result, TIME_FORMAT(test.user_time,'%i:%S') user_time, test.test_date test_date, test.question_id,test.guess 	FROM cp_test_results test  	WHERE test.question_id="+questionid+" and test.userid = "+json.get("userId").toString()+"  order by test.test_date desc limit 5) 		UNION ALL 		(SELECT qh.question_status, TIME_FORMAT(qh.question_time,'%i:%S') user_time,qh.question_date test_date ,qh.question_id,qh.question_guess 	FROM cp_question_history qh where qh.user_id= "+json.get("userId").toString()+"  and qh.question_id="+questionid+"  order by qh.question_date desc limit 5 ) order by   test_date  limit 5";
				
              // System.out.println("  *** STEP 3: History :\n\t"+qry); 

				
				questionHistory= con.createStatement();
				questionHistoryres = questionHistory.executeQuery(qry);
				
				while(questionHistoryres.next())
				{
						String res =   questionHistoryres.getString("result");
						String usertime = questionHistoryres.getString("user_time");
						String tdate = questionHistoryres.getString("test_date");
						String guessdata = questionHistoryres.getString("guess");
						SimpleDateFormat mdyFormatres = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						SimpleDateFormat dmyFormatres = new SimpleDateFormat("MMM dd yyy hh:mm a");						    
						tdate=dmyFormatres.format(mdyFormatres.parse(tdate));				    
						
						if(res.equalsIgnoreCase("correct"))
						{
						
			                hisdiv = hisdiv+ "<span class=@font-15@><i class=@fa fa-check-circle text-success@></i><span class=@p-l-10 p-r-10@>Correct</span><i class=@fa fa-arrow-right@></i> <i class=@fa fa-clock-circle@></i> <span class=@p-l-10 p-r-10@>"+usertime+" ("+tdate+")  "+guessdata+"</span></span><br>";
			              
						}
						else
						{
							hisdiv = hisdiv+ "<span class=@font-15@><i class=@fa fa-times-circle text-danger@></i><span class=@p-l-10 p-r-10 text-danger@> Wrong</span><i class=@fa fa-arrow-right@></i> <i class=@fa fa-clock-circle@></i> <span class=@p-l-10 p-r-10@>"+usertime+" ("+tdate+")  "+guessdata+"</span></span><br>";
						}
						
					//System.out.println("The Question History data is----------------------------"+hisdiv);
				}
								
				 analysisBO.put("history",hisdiv);
		}
			questioArrayList1.put(analysisBO);
			//System.out.println("The data is---------------------------"+analysisBO.toString());	
		}
		
		
		//System.out.println("the total data is"+questioArrayList1.toString()+"\n\t the coount is"+i);	
		
				
String fastestSlowestQuery = "(select ad.*,qm.question_title from(select * from ((SELECT  'QMaxCorrect' questiontype, test.row_id rowidd,test.result result,test.question_id questionid,test.userid userid,test.test_date testdate,test.test_master_id,test.user_time usertime,test.section_id sectionid,test.guess,test.flagstatus,test.test_no,test.type_id"
+ " FROM cp_test_results test   where test.userid ='"+ json.get("userId").toString()+"'  order by test.question_id ) union" 
+ "(select   'QMaxCorrect' questiontype, qh.question_history_id rowidd,qh.question_status result ,qh.question_id questionid,qh.user_id  userid ,qh.question_date testdate,qh.test_master_id ,qh.question_time usertime,qh.section_id sectionid ,qh.question_guess,'',qh.testno,qh.typeid    from cp_question_history qh where qh.user_id='"+ json.get("userId").toString()+"'"
+ "ORDER BY qh.question_id)) as a order by questionid ,testdate desc ) ad ,cp_question_masters qm where ad.questionid=qm.question_id   and  sectionid=1 and result='CORRECT' group by questionid order by usertime desc limit 1 )"
+ "union"
+ "(select ad.*,qm.question_title from(select * from ((SELECT  'QMaxINCorrect' questiontype, test.row_id rowidd,test.result result,test.question_id questionid,test.userid userid,test.test_date testdate,test.test_master_id,test.user_time usertime,test.section_id sectionid,test.guess,test.flagstatus,test.test_no,test.type_id"
+ " FROM cp_test_results test   where test.userid ='"+ json.get("userId").toString()+"'  order by test.question_id ) "
+ "union "
+ "(select   'QMaxINCorrect' questiontype, qh.question_history_id rowidd,qh.question_status result ,qh.question_id questionid,qh.user_id  userid ,qh.question_date testdate,qh.test_master_id ,qh.question_time usertime,qh.section_id sectionid ,qh.question_guess,'' ,qh.testno,qh.typeid   from cp_question_history qh where qh.user_id='"+ json.get("userId").toString()+"'"
+ "ORDER BY qh.question_id)) as a order by questionid ,testdate desc ) ad ,cp_question_masters qm where ad.questionid=qm.question_id and sectionid=1 and result='INCORRECT' group by questionid order by usertime desc limit 1 )"
+ "union"
+ "(select ad.*,qm.question_title from(select * from ((SELECT  'QMinCorrect' questiontype, test.row_id rowidd,test.result result,test.question_id questionid,test.userid userid,test.test_date testdate,test.test_master_id,test.user_time usertime,test.section_id sectionid,test.guess,test.flagstatus,test.test_no,test.type_id"
+ " FROM cp_test_results test   where test.userid ='"+ json.get("userId").toString()+"'  order by test.question_id ) "
+ "union "
+ "(select   'QMinCorrect' questiontype, qh.question_history_id rowidd,qh.question_status result ,qh.question_id questionid,qh.user_id  userid ,qh.question_date testdate,qh.test_master_id ,qh.question_time usertime,qh.section_id sectionid ,qh.question_guess,'' ,qh.testno,qh.typeid   from cp_question_history qh where qh.user_id='"+ json.get("userId").toString()+"'"
+ "ORDER BY qh.question_id)) as a order by questionid ,testdate desc ) ad ,cp_question_masters qm where ad.questionid=qm.question_id  and sectionid=1 and result='CORRECT' group by questionid order by usertime  limit 1 )"
+ "union"
+ "(select ad.*,qm.question_title from(select * from ((SELECT  'QMinINCorrect' questiontype, test.row_id rowidd,test.result result,test.question_id questionid,test.userid userid,test.test_date testdate,test.test_master_id,test.user_time usertime,test.section_id sectionid,test.guess,test.flagstatus,test.test_no,test.type_id"
+ " FROM cp_test_results test   where test.userid ='"+ json.get("userId").toString()+"'  order by test.question_id )"
+ "union "
+ "(select   'QMinINCorrect' questiontype, qh.question_history_id rowidd,qh.question_status result ,qh.question_id questionid,qh.user_id  userid ,qh.question_date testdate,qh.test_master_id ,qh.question_time usertime,qh.section_id sectionid ,qh.question_guess,'',qh.testno,qh.typeid    from cp_question_history qh where qh.user_id='"+ json.get("userId").toString()+"'"
+ "ORDER BY qh.question_id)) as a order by questionid ,testdate desc ) ad ,cp_question_masters qm where ad.questionid=qm.question_id  and sectionid=1 and result='INCORRECT' group by questionid order by usertime  limit 1 )"
+ "union"
+ "(select ad.*,qm.question_title from(select * from ((SELECT  'VMaxCorrect' questiontype, test.row_id rowidd,test.result result,test.question_id questionid,test.userid userid,test.test_date testdate,test.test_master_id,test.user_time usertime,test.section_id sectionid,test.guess,test.flagstatus,test.test_no,test.type_id"
+ " FROM cp_test_results test   where test.userid ='"+ json.get("userId").toString()+"'  order by test.question_id ) "
+ "union "
+ "(select   'VMaxCorrect' questiontype, qh.question_history_id rowidd,qh.question_status result ,qh.question_id questionid,qh.user_id  userid ,qh.question_date testdate,qh.test_master_id ,qh.question_time usertime,qh.section_id sectionid ,qh.question_guess,'',qh.testno,qh.typeid    from cp_question_history qh where qh.user_id='"+ json.get("userId").toString()+"'"
+ "ORDER BY qh.question_id)) as a order by questionid ,testdate desc ) ad ,cp_question_masters qm where ad.questionid=qm.question_id  and sectionid=2 and result='CORRECT' group by questionid order by usertime desc limit 1 )"
+ "union"
+ "(select ad.*,qm.question_title from(select * from ((SELECT  'VMaxINCorrect' questiontype, test.row_id rowidd,test.result result,test.question_id questionid,test.userid userid,test.test_date testdate,test.test_master_id,test.user_time usertime,test.section_id sectionid,test.guess,test.flagstatus,test.test_no,test.type_id"
+ " FROM cp_test_results test   where test.userid ='"+ json.get("userId").toString()+"'  order by test.question_id ) "
+ "union "
+ "(select   'VMaxINCorrect' questiontype, qh.question_history_id rowidd,qh.question_status result ,qh.question_id questionid,qh.user_id  userid ,qh.question_date testdate,qh.test_master_id,qh.question_time usertime,qh.section_id sectionid ,qh.question_guess,'',qh.testno,qh.typeid    from cp_question_history qh where qh.user_id='"+ json.get("userId").toString()+"'"
+ "ORDER BY qh.question_id)) as a order by questionid ,testdate desc ) ad ,cp_question_masters qm where ad.questionid=qm.question_id  and sectionid=2 and result='INCORRECT' group by questionid order by usertime desc limit 1 )"
+ "union"
+ "(select ad.*,qm.question_title from(select * from ((SELECT  'VMinCorrect' questiontype, test.row_id rowidd,test.result result,test.question_id questionid,test.userid userid,test.test_date testdate,test.test_master_id,test.user_time usertime,test.section_id sectionid,test.guess,test.flagstatus,test.test_no,test.type_id"
+ " FROM cp_test_results test   where test.userid ='"+ json.get("userId").toString()+"'  order by test.question_id ) "
+ "union "
+ "(select   'VMinCorrect' questiontype, qh.question_history_id rowidd,qh.question_status result ,qh.question_id questionid,qh.user_id  userid ,qh.question_date testdate,qh.test_master_id ,qh.question_time usertime,qh.section_id sectionid ,qh.question_guess,'',qh.testno,qh.typeid    from cp_question_history qh where qh.user_id='"+ json.get("userId").toString()+"'"
+ "ORDER BY qh.question_id)) as a order by questionid ,testdate desc ) ad ,cp_question_masters qm where ad.questionid=qm.question_id  and sectionid=2 and result='CORRECT' group by questionid order by usertime  limit 1 )"
+ "union"
+ "(select ad.*,qm.question_title from(select * from ((SELECT  'VMinINCorrect' questiontype, test.row_id rowidd,test.result result,test.question_id questionid,test.userid userid,test.test_date testdate,test.test_master_id,test.user_time usertime,test.section_id sectionid,test.guess,test.flagstatus,test.test_no,test.type_id"
+ " FROM cp_test_results test   where test.userid ='"+ json.get("userId").toString()+"'  order by test.question_id ) "
+ "union "
+ "(select   'VMinINCorrect' questiontype, qh.question_history_id rowidd,qh.question_status result ,qh.question_id questionid,qh.user_id  userid ,qh.question_date testdate,qh.test_master_id ,qh.question_time usertime,qh.section_id sectionid ,qh.question_guess,'',qh.testno,qh.typeid    from cp_question_history qh where qh.user_id='"+ json.get("userId").toString()+"'"
+ "ORDER BY qh.question_id)) as a order by questionid ,testdate desc ) ad ,cp_question_masters qm where ad.questionid=qm.question_id  and sectionid=2 and result='INCORRECT' group by questionid order by usertime  limit 1 )";
		
		
		System.out.println("Thw query is-------------------------------"+fastestSlowestQuery);
		JSONObject    totaldata=new JSONObject();
		
		rs = st.executeQuery(fastestSlowestQuery);
		while(rs.next())
		{
			JSONObject obj=new JSONObject();
			
			obj.put("Questiontype", rs.getString("questiontype"));
			obj.put("Result", rs.getString("result"));
			obj.put("Questionid", rs.getString("questionid"));
			obj.put("UserId", rs.getString("userid"));
			obj.put("Testdate", rs.getString("testdate"));
			obj.put("UserTime", rs.getString("usertime"));
			obj.put("SectionId", rs.getString("sectionid"));
			obj.put("Guess", rs.getString("guess"));
			obj.put("FlagStatus", rs.getString("flagstatus"));
			
			obj.put("testno",rs.getString("test_no"));
			obj.put("typeid",rs.getString("type_id"));
			obj.put("testmasterid",rs.getString("test_master_id"));
			obj.put("QuestionTitle", "<a href=Get-Full-Session-Retry-Questions.action?ques_id="+obj.get("Questionid")+"&type_id="+obj.get("typeid")+"&retrytestno="+obj.get("testno")+"&sectionnumber="+obj.get("SectionId")+">"+rs.getString("question_title")+"</a>");
	    	totaldata.put(rs.getString("questiontype"), obj);
			
		}		
		
		//System.out.println("The Total Data is-------------------------------------"+totaldata.toString());
		
		
		if(questioArrayList==null)
		{
			questioArrayList=new JSONObject();
		}
		TotalSolvedCount=correctCount+incorrectCount;
		resultJson.put("pie-chart-data", correctCount + "@" + incorrectCount+"@"+TotalSolvedCount+"@"+TotalPoolQuestion);
		resultJson.put("correctcount", correctCount);
		resultJson.put("Incorrectcount", incorrectCount);
		resultJson.put("solvedcount", TotalSolvedCount);
		resultJson.put("totalpoolquestions", TotalPoolQuestion);
		resultJson.put("Leftquestions", TotalPoolQuestion-TotalSolvedCount);		
		
		
		questioArrayList.put("tabledata",questioArrayList1);
		questioArrayList.put("graphsdata",resultJson.toString());
		questioArrayList.put("fastslowques",totaldata.toString());
		System.out.println("The json data"+resultJson.toString());
		DBUtil.closeConnection(rs, st, con);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally {
			DBUtil.closeConnection(rs, st, con);
		}
		
		return questioArrayList;
	}

	
	
	public boolean updateSectionStatusforNextSection(JSONObject json)
			throws ConnectionException, JSONException {

		boolean result = false;
		ResultSet rs1 = null;
		Statement st = null;
		Connection con = null;
		con = DatabaseConnection.getCrunchPrepConnection();
		try {
			st = con.createStatement();
			if(json.getInt("tot_sec")==0){
			
			
			String sql1 = "select test.flength_rowId from `cp_fulllength_test` test where test.userId like '"
					+ json.getInt("userId")
					+ "' order by test.test_starting_date desc";
			rs1 = st.executeQuery(sql1);
			if (rs1.next()) {
				String sql = "UPDATE  `cp_fulllength_test` SET `section_Id`= CASE WHEN `section_Id`=1 THEN 2 ELSE 1 End,   `section_number`=`section_number`+1, `section_status`='INCOMPLETE'"
						+ "	 WHERE  `flength_rowId`= " + rs1.getInt(1);
				int i = st.executeUpdate(sql);
				if (i == 1) {
					result = true;
				}
			}
			}
			else
			{ 
				String sql="UPDATE  `cp_fulllength_test` SET `status`='COMPLETE' WHERE  `flength_rowId`="+json.getInt("fullLengthRowId");
				int i=st.executeUpdate(sql);
				if(i>0)
				{
					System.out.println("Full Length Test Status Updated Successfully");
				}
				else
				{
					System.out.println("Full Length Test Status Not  Updated Successfully");
				} 
				
			}
			
			
			
			
		} catch (SQLException e) {

			e.printStackTrace();
		}

		finally {
			DBUtil.closeConnection(rs1, st, con);
		}

		return result;

	}

	public double getDifficultyLevel(org.json.JSONObject object)
			throws ConnectionException, JSONException {
		double result = 0.0000;
		int sectionNumber = 0, userId = 0;

		sectionNumber = object.getInt("sectionNumber");
		userId = object.getInt("userId");

		ResultSet rs = null;
		Statement st = null;
		Connection con = null;
		con = DatabaseConnection.getCrunchPrepConnection();
		String sql = "select secDiff from cp_fulllength_test ft where ft.userId like '"
				+ userId + "' order by ft.test_starting_date";
		if (sectionNumber == 3) {
			sql = sql.replace("secDiff", "ft.section_1_difficulty");
		}
		if (sectionNumber == 4) {
			sql = sql.replace("secDiff", "ft.section_2_difficulty");
		}
		System.out.println("Query for NExt Generating Section Difficulty is : "
				+ sql + "\t sectionNumber " + sectionNumber);

		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next()) {
				result = rs.getDouble(1);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		finally {
			DBUtil.closeConnection(rs, st, con);
		}

		return result;
	}

	
	public boolean retrySave(QuestionsUploadBO uploadBO) throws CPException{
		// TODO Auto-generated method stub
		boolean result=false;
		Statement st=null,st1=null;
		ResultSet rs=null; 
		    	Connection connection=null; 
				try{ 
					connection=DatabaseConnection.getCrunchPrepConnection(); 
					st=connection.createStatement();
					st1=connection.createStatement();   
					String query="INSERT INTO `cp_question_history` (`user_id`, `question_id`, `question_date`, `question_time`, `question_status`, `question_guess`,section_id,testno,test_master_id,typeid) VALUES ('"+uploadBO.getUser_id()+"', '"+uploadBO.getQuestion_id()+"', CURRENT_TIMESTAMP(), '"+uploadBO.getUser_time()+"', '"+uploadBO.getResult()+"', '"+uploadBO.getGuessed()+"','"+uploadBO.getSection_id()+"',"+uploadBO.getTest_no()+",2,"+uploadBO.getTypeId()+")";
					
					String query1="update cp_test_results   set   flagstatus='"+uploadBO.getFlag_status()+"' where  userid ="+uploadBO.getUser_id()+"   and question_id="+uploadBO.getQuestion_id()+" and section_id="+uploadBO.getSection_id()+" and test_no="+uploadBO.getTest_no()+" ";
					System.out.println("Retry :"+query+"\nthe update query-----------------------------------------------------------"+query1);
					int n = st.executeUpdate(query);
					if (n > 0) {
						//System.out.println("nserrt doneeeeeeeeeeeeeeeee");
						int n1 = st1.executeUpdate(query1);
						System.out.println("the value sis"+n1);
						result = false;
					}
					connection.close(); 
				}
				catch(ConnectionException exp){ 
					throw new ConnectionException(exp); 
				}
				catch(Exception exp)
				{
					exp.printStackTrace();
					throw new CommonExceptions(exp); 
				}
				finally{
					try {
						connection.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						throw new CPException(e);
					}
				} 
		return result;
	}

	@Override
	public List<QuestionsUploadBO> getPredictedScoreDetails(int userid, int testNo,
			int testid)throws CPException {
		// TODO Auto-generated method stub
		    List<QuestionsUploadBO> predictedScoreList=new ArrayList<QuestionsUploadBO>(); 
			Statement st=null;
			ResultSet rs=null; 
			    	Connection connection=null; 
					try{ 
						connection=DatabaseConnection.getCrunchPrepConnection(); 
						st=connection.createStatement();
						 
						String query="select * from cp_test_results r where r.userid like '"+userid+"' and r.test_no like '"+testNo+"' and r.test_master_id like '"+testid+"' "; 
						rs = st.executeQuery(query);
						while(rs.next()){
							QuestionsUploadBO scoreBo=new QuestionsUploadBO();
							scoreBo.setResult(rs.getString("result"));
							scoreBo.setDifficulty_id(rs.getInt("diff_id"));
							scoreBo.setQuestion_no(rs.getInt("question_id"));
							scoreBo.setSection_id(rs.getInt("section_id"));	
							predictedScoreList.add(scoreBo);
							}
					 
						connection.close(); 
					}
					catch(ConnectionException exp){ 
						throw new ConnectionException(exp); 
					}
					catch(Exception exp)
					{
						exp.printStackTrace();
						throw new CommonExceptions(exp); 
					}
					finally{
						try {
							connection.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							throw new CPException(e);
						}
					}  
		 return predictedScoreList;
	}

	@Override
	public List<ScoreBO> getScaledScoreRanges() throws CPException {
		// TODO Auto-generated method stub
		List<ScoreBO> scaledScoreRanges=new ArrayList<ScoreBO>();
		Statement st=null,st1=null;
		ResultSet rs=null; 
		    	Connection connection=null; 
				try{ 
					connection=DatabaseConnection.getCrunchPrepConnection(); 
					st=connection.createStatement();
					st1=connection.createStatement();   
					String query="select * from cp_gre_scores";  
					 rs=st.executeQuery(query);
					 while(rs.next()){
						 ScoreBO scoreBO=new ScoreBO();
						 scoreBO.setScaledScoreRange(rs.getDouble("scaled_score"));
						 scoreBO.setQuantScaledScoreRange(rs.getDouble("quant"));
						 scoreBO.setVerbalScaledScoreRange(rs.getDouble("verbal"));
						 scaledScoreRanges.add(scoreBO);
					 }
					//connection.close(); 
				}
				catch(ConnectionException exp){ 
					throw new ConnectionException(exp); 
				}
				catch(Exception exp)
				{
					exp.printStackTrace();
					throw new CommonExceptions(exp); 
				}
				finally{
					try {
						connection.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						throw new CPException(e);
					}
				} 
		return scaledScoreRanges;
	} 
	
	
/****************************************  save GRE SCORE  START ************************/

public int saveGreScore(ScoreBO bo) throws CPException{
	int res = 0;
	Statement st=null,st1=null;
	ResultSet rs=null; 
	    	Connection connection=null; 
			try{ 
				connection=DatabaseConnection.getCrunchPrepConnection(); 
				st=connection.createStatement();
				st1=connection.createStatement();   
				String query="INSERT INTO `cp_test_score` (`user_id`, `test_master_id`, `test_no`, `totalQuantScaledScore`, `totalVerbalScaledScore`, `quantPercentile`, `verbalPercentile`, `totalScore`, `QuantRawScore`, `VerbalRawScore`) VALUES" +
				
						"								  ("+bo.getUserid()+", 2, "+bo.getTestno()+", "+bo.getTotalQuantScaledScore()+", "+bo.getTotalVerbalScaledScore()+", "+bo.getQuantPercentile()+", "+bo.getVerbalPercentile()+", "+bo.getTotalScore()+", "+bo.getQuantRawScore()+","+bo.getVerbalRawScore()+")";
				
				System.out.println(" GRE QUERTY ----------------->"+query);
				res =st.executeUpdate(query);
				if(res>0)
				{
					System.out.println("in sert DONE");
				}
				
				 
			}
			catch(ConnectionException exp){ 
				throw new ConnectionException(exp); 
			}
			catch(Exception exp)
			{
				exp.printStackTrace();
				throw new CommonExceptions(exp); 
			}
			finally{
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					throw new CPException(e);
				}
			} 
	
	
	return res;
}
	
/****************************************  save GRE SCORE end************************/	
		
}
