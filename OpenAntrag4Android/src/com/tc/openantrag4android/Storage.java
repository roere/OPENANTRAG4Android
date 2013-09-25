package com.tc.openantrag4android;

import java.util.ArrayList;

import com.openantrag4J.representation.Representation;
import com.openantrag4J.representation.android.Representative;
import com.openantrag4j.proposal.Comment;
import com.openantrag4j.proposal.Proposal;
import com.openantrag4j.proposal.ProposalFile;

public abstract class Storage {

	public static ProposalFile proposalFile = null;
	public static Proposal proposal = null;
	public static Representative representative = null;
	public static ArrayList<Representation> representationList = null;
	public static String representationKey = null;
	public static ArrayList<Comment> comments = null;
	public static Integer currentPage = 1;
	public static Long lastReload = System.currentTimeMillis();

}
