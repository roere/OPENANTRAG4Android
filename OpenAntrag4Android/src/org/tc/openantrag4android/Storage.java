package org.tc.openantrag4android;

import java.util.ArrayList;

import org.tc.openantrag4J.representation.Representation;
import org.tc.openantrag4J.representation.RepresentationSet;
import org.tc.openantrag4J.representation.android.Representative;
import org.tc.openantrag4j.proposal.Comment;
import org.tc.openantrag4j.proposal.Proposal;
import org.tc.openantrag4j.proposal.ProposalSet;

public abstract class Storage {

	public static ProposalSet proposalFile = null;
	public static Proposal proposal = null;
	public static Representative representative = null;
	public static ArrayList<Representation> representationList = null;
	public static ArrayList<String> tagList = null;
	public static ArrayList<Integer> proposalCount = null;
	public static String representationKey = null;
	public static String tag = null;
	public static ArrayList<Comment> comments = null;
	public static Integer currentPage = 1;
	public static Long lastReloadProposalPage = System.currentTimeMillis();
	public static Long lastReloadMainPage = System.currentTimeMillis();

}
