package com.test.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.mysql.jdbc.Connection;
import com.test.model.Notes;
import com.test.util.NotesUtil;

public class JDBCDAO {
	
	@SuppressWarnings("unchecked")
	/*
	 * Below method getNotes return List of Notes related to this Users
	 * */
	public ArrayList<Notes> getNotes(String emailId) throws SQLException, ClassNotFoundException{
		Connection session=NotesUtil.getSession();
		java.sql.PreparedStatement ps=session.prepareStatement("select * from NoteInfo where userId in(Select userId FROM USERSINFO WHERE emailId=?)");
		ps.setString(1, emailId);
		ResultSet rs=ps.executeQuery();
		ArrayList<Notes> notesList=new ArrayList<Notes>();
		while(rs.next()){
			Notes notes=new Notes();
			
			notes.setNoteId(rs.getString("noteId"));
			notes.setTitle(rs.getString("title"));
			notes.setNote(rs.getString("note"));
			notes.setCreateTime(rs.getDate("createTime"));
			notes.setLastUpdateTime(rs.getDate("lastUpdateTime"));
			notes.setNoteId(rs.getString("userId"));
			notesList.add(notes);
		}
		
		return notesList;
	}
	/*
	 * Below method add notes will add notes and return List of Notes related to this Users
	 * */
	public ArrayList<Notes> addNotes(Notes notes,String emailId) throws SQLException, ClassNotFoundException{
		
		Connection session=NotesUtil.getSession();
		
		java.sql.PreparedStatement ps=session.prepareStatement("Select userId FROM USERSINFO WHERE emailId=?");
		ps.setString(1, emailId);
		ResultSet rs=ps.executeQuery();
		String userId=null;
		if(rs.next()){
			userId=rs.getString(1);
		}

		java.sql.PreparedStatement ps1=session.prepareStatement("INSERT into NoteInfo(noteId,title,note,createTime,userId) values(?,?,?,?,?)");
		ps1.setString(1, notes.getNoteId());
		ps1.setString(2, notes.getTitle());
		ps1.setString(3, notes.getNote());
		ps1.setDate(4, new java.sql.Date(new Date().getTime()));
		ps1.setString(5, userId);
		ps1.executeUpdate();
		java.sql.PreparedStatement ps3=session.prepareStatement("select NoteId,title"
				+ ",note,createTime,lastUpdateTime,userId from NoteInfo where userId in(Select userId FROM USERSINFO WHERE emailId=?)");
		ps3.setString(1, emailId);
		ResultSet rs1=ps3.executeQuery();
		ArrayList<Notes> notesList=new ArrayList<Notes>();
		while(rs1.next()){
			Notes notes1=new Notes();
			
			notes1.setNoteId(rs1.getString("NoteId"));
			notes1.setTitle(rs1.getString("title"));
			notes1.setNote(rs1.getString("note"));
			notes1.setCreateTime(rs1.getDate("createTime"));
			notes1.setLastUpdateTime(rs1.getDate("lastUpdateTime"));
			notes1.setUserId(rs1.getString("userId"));
			notesList.add(notes1);
		}
		
		return notesList;
		
	}
	/*
	 * Below method updateNotes using noteId and return List of Notes related to this Users
	 * */
	
	public ArrayList<Notes> updateNotes(Notes notes,String emailId) throws SQLException, ClassNotFoundException{
		
		Connection session=NotesUtil.getSession();
		
		java.sql.PreparedStatement ps1=session.prepareStatement("update  NoteInfo set title=?,note=?,"
				+ "lastUpdateTime=? where noteId=? and userId in( Select userId FROM USERSINFO WHERE emailId=?)");
		
		ps1.setString(1, notes.getTitle());
		ps1.setString(2, notes.getNote());
		ps1.setDate(3, new java.sql.Date(new Date().getTime()));
		ps1.setString(4, notes.getNoteId());
		ps1.setString(5, emailId);
		ps1.executeUpdate();
		java.sql.PreparedStatement ps3=session.prepareStatement("select NoteId,title"
				+ ",note,createTime,lastUpdateTime,userId from NoteInfo where userId in(Select userId FROM USERSINFO WHERE emailId=?)");
		ps3.setString(1, emailId);
		ResultSet rs1=ps3.executeQuery();
		ArrayList<Notes> notesList=new ArrayList<Notes>();
		while(rs1.next()){
			Notes notes1=new Notes();
			
			notes1.setNoteId(rs1.getString("NoteId"));
			notes1.setTitle(rs1.getString("title"));
			notes1.setNote(rs1.getString("note"));
			notes1.setCreateTime(rs1.getDate("createTime"));
			notes1.setLastUpdateTime(rs1.getDate("lastUpdateTime"));
			notes1.setUserId(rs1.getString("userId"));
			notesList.add(notes1);
		}
		
		return notesList;
	}
	
	/*
	 * Below method delete notes using noteId and return List of Notes related to this Users
	 * */
	public ArrayList<Notes> deleteNotes(String noteId,String emailId) throws SQLException, ClassNotFoundException{
		Connection session=NotesUtil.getSession();
		java.sql.PreparedStatement ps1=session.prepareStatement("delete from  NoteInfo where noteId=? and "
				+ "userId in( Select userId FROM USERSINFO WHERE emailId=?)");
		
		ps1.setString(1, noteId);
		ps1.setString(2, emailId);
		ps1.executeUpdate();
		java.sql.PreparedStatement ps3=session.prepareStatement("select NoteId,title"
				+ ",note,createTime,lastUpdateTime,userId from NoteInfo where userId in(Select userId FROM USERSINFO WHERE emailId=?)");
		ps3.setString(1, emailId);
		ResultSet rs1=ps3.executeQuery();
		ArrayList<Notes> notesList=new ArrayList<Notes>();
		while(rs1.next()){
			Notes notes1=new Notes();
			
			notes1.setNoteId(rs1.getString("NoteId"));
			notes1.setTitle(rs1.getString("title"));
			notes1.setNote(rs1.getString("note"));
			notes1.setCreateTime(rs1.getDate("createTime"));
			notes1.setLastUpdateTime(rs1.getDate("lastUpdateTime"));
			notes1.setUserId(rs1.getString("userId"));
			notesList.add(notes1);
		}
		
		return notesList;
		
	}
	/*
	 * Below method validate user authentication using emaild and password
	 * */
	
	public boolean isUserAuthenticated(String emailId,String password){
		try{
			Connection session=NotesUtil.getSession();
			java.sql.PreparedStatement ps1=session.prepareStatement("Select * FROM USERSINFO WHERE emailId=? and password=?");
			ps1.setString(1, emailId);
			ps1.setString(2, password);
			ResultSet rs=ps1.executeQuery();
			if(rs.next()){
				return true;
			}
			}catch(Exception e){
				return false;
			}
		return false;
	}
}
