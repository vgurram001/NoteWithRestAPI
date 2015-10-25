package com.test.rest;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.test.model.Notes;
import com.test.util.NotesUtil;

@Path("/note")
public class NoteService {

	
	
	@GET
	@Path("/getNote")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Notes> getUserNotes(@HeaderParam("emailId")String emailId) throws SQLException, ClassNotFoundException{
		
		return NotesUtil.getJDBCDAO().getNotes(emailId);
		
	}
	
	@POST
	@Path("/addNote")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Notes> addUserNotes(Notes noteObj,@HeaderParam("emailId")String emailId) throws SQLException, ClassNotFoundException{
		return NotesUtil.getJDBCDAO().addNotes(noteObj, emailId);
				
	} 
	
	@PUT
	@Path("/updateNote/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Notes> updateUserNotes(Notes noteObj,@HeaderParam("emailId")String emailId) throws SQLException, ClassNotFoundException{
		return NotesUtil.getJDBCDAO().updateNotes(noteObj, emailId);
	}
	
	@DELETE
	@Path("/deleteNote/{noteId}")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Notes> deleteUserNotes(@PathParam("noteId")String noteId,@HeaderParam("emailId")String emailId) throws SQLException, ClassNotFoundException{
		return NotesUtil.getJDBCDAO().deleteNotes(noteId,emailId);
	}
}
