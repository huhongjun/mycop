# MySQL-Front Dump 2.5
#
# Host: localhost   Database: shark
# --------------------------------------------------------
# Server version 4.1.5-gamma-nt


#
# Table structure for table 'activities'
#

CREATE TABLE activities (
  Id varchar(254) NOT NULL default '',
  ActivitySetDefinitionId varchar(100) default NULL,
  ActivityDefinitionId varchar(100) NOT NULL default '',
  Process decimal(19,0) NOT NULL default '0',
  TheResource decimal(19,0) default NULL,
  State decimal(19,0) NOT NULL default '0',
  BlockActivity decimal(19,0) default NULL,
  Priority int(11) default NULL,
  Name varchar(254) default NULL,
  Activated double default NULL,
  Accepted double default NULL,
  LastStateTime double default NULL,
  Description varchar(254) default NULL,
  oid decimal(19,0) NOT NULL default '0',
  version int(11) NOT NULL default '0',
  PRIMARY KEY  (oid),
  UNIQUE KEY I1_Activities (Id),
  KEY I2_Activities (Process),
  KEY I3_Activities (Process,ActivitySetDefinitionId,ActivityDefinitionId)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;



#
# Dumping data for table 'activities'
#



#
# Table structure for table 'activitydata'
#

CREATE TABLE activitydata (
  Activity decimal(19,0) NOT NULL default '0',
  VariableDefinitionId varchar(254) NOT NULL default '',
  VariableValue mediumblob NOT NULL,
  IsResult smallint(6) NOT NULL default '0',
  oid decimal(19,0) NOT NULL default '0',
  version int(11) NOT NULL default '0',
  PRIMARY KEY  (oid),
  UNIQUE KEY I1_ActivityData (Activity,VariableDefinitionId),
  KEY I2_ActivityData (Activity)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;



#
# Dumping data for table 'activitydata'
#



#
# Table structure for table 'activitystateeventaudits'
#

CREATE TABLE activitystateeventaudits (
  KeyValue varchar(30) NOT NULL default '',
  Name varchar(50) NOT NULL default '',
  oid decimal(19,0) NOT NULL default '0',
  version int(11) NOT NULL default '0',
  PRIMARY KEY  (oid),
  UNIQUE KEY I1_ActivityStateEventAudits (KeyValue),
  UNIQUE KEY I2_ActivityStateEventAudits (Name)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;



#
# Dumping data for table 'activitystateeventaudits'
#

INSERT INTO activitystateeventaudits (KeyValue, Name, oid, version) VALUES("open.running", "open.running", "1000013", "0");
INSERT INTO activitystateeventaudits (KeyValue, Name, oid, version) VALUES("open.not_running.not_started", "open.not_running.not_started", "1000015", "0");
INSERT INTO activitystateeventaudits (KeyValue, Name, oid, version) VALUES("open.not_running.suspended", "open.not_running.suspended", "1000017", "0");
INSERT INTO activitystateeventaudits (KeyValue, Name, oid, version) VALUES("closed.completed", "closed.completed", "1000019", "0");
INSERT INTO activitystateeventaudits (KeyValue, Name, oid, version) VALUES("closed.terminated", "closed.terminated", "1000021", "0");
INSERT INTO activitystateeventaudits (KeyValue, Name, oid, version) VALUES("closed.aborted", "closed.aborted", "1000023", "0");


#
# Table structure for table 'activitystates'
#

CREATE TABLE activitystates (
  KeyValue varchar(30) NOT NULL default '',
  Name varchar(50) NOT NULL default '',
  oid decimal(19,0) NOT NULL default '0',
  version int(11) NOT NULL default '0',
  PRIMARY KEY  (oid),
  UNIQUE KEY I1_ActivityStates (KeyValue),
  UNIQUE KEY I2_ActivityStates (Name)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;



#
# Dumping data for table 'activitystates'
#

INSERT INTO activitystates (KeyValue, Name, oid, version) VALUES("open.running", "open.running", "1000001", "0");
INSERT INTO activitystates (KeyValue, Name, oid, version) VALUES("open.not_running.not_started", "open.not_running.not_started", "1000003", "0");
INSERT INTO activitystates (KeyValue, Name, oid, version) VALUES("open.not_running.suspended", "open.not_running.suspended", "1000005", "0");
INSERT INTO activitystates (KeyValue, Name, oid, version) VALUES("closed.completed", "closed.completed", "1000007", "0");
INSERT INTO activitystates (KeyValue, Name, oid, version) VALUES("closed.terminated", "closed.terminated", "1000009", "0");
INSERT INTO activitystates (KeyValue, Name, oid, version) VALUES("closed.aborted", "closed.aborted", "1000011", "0");


#
# Table structure for table 'andjointable'
#

CREATE TABLE andjointable (
  Process decimal(19,0) NOT NULL default '0',
  ActivitySetDefinitionId varchar(100) default NULL,
  ActivityDefinitionId varchar(100) NOT NULL default '',
  Activity decimal(19,0) NOT NULL default '0',
  CNT decimal(19,0) NOT NULL default '0',
  oid decimal(19,0) NOT NULL default '0',
  version int(11) NOT NULL default '0',
  PRIMARY KEY  (oid),
  UNIQUE KEY I1_AndJoinTable (CNT),
  KEY I2_AndJoinTable (Process,ActivitySetDefinitionId,ActivityDefinitionId)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;



#
# Dumping data for table 'andjointable'
#



#
# Table structure for table 'assignmenteventaudits'
#

CREATE TABLE assignmenteventaudits (
  UTCTime varchar(254) NOT NULL default '',
  TheType decimal(19,0) NOT NULL default '0',
  ActivityId varchar(254) NOT NULL default '',
  ActivityName varchar(254) default NULL,
  ProcessId varchar(254) NOT NULL default '',
  ProcessName varchar(254) default NULL,
  ProcessDefinitionName varchar(254) NOT NULL default '',
  ProcessDefinitionVersion varchar(20) NOT NULL default '',
  ActivityDefinitionId varchar(100) NOT NULL default '',
  ActivitySetDefinitionId varchar(100) default NULL,
  ProcessDefinitionId varchar(100) NOT NULL default '',
  PackageId varchar(100) NOT NULL default '',
  OldResourceUsername varchar(125) default NULL,
  OldResourceName varchar(100) default NULL,
  NewResourceUsername varchar(125) NOT NULL default '',
  NewResourceName varchar(100) default NULL,
  IsAccepted smallint(6) NOT NULL default '0',
  CNT decimal(19,0) NOT NULL default '0',
  oid decimal(19,0) NOT NULL default '0',
  version int(11) NOT NULL default '0',
  PRIMARY KEY  (oid),
  UNIQUE KEY I1_AssignmentEventAudits (CNT)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;



#
# Dumping data for table 'assignmenteventaudits'
#



#
# Table structure for table 'assignmentstable'
#

CREATE TABLE assignmentstable (
  Activity decimal(19,0) NOT NULL default '0',
  TheResource decimal(19,0) NOT NULL default '0',
  CNT decimal(19,0) NOT NULL default '0',
  oid decimal(19,0) NOT NULL default '0',
  version int(11) NOT NULL default '0',
  PRIMARY KEY  (oid),
  UNIQUE KEY I1_AssignmentsTable (CNT),
  UNIQUE KEY I2_AssignmentsTable (Activity,TheResource),
  KEY I3_AssignmentsTable (Activity),
  KEY I4_AssignmentsTable (TheResource)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;



#
# Dumping data for table 'assignmentstable'
#



#
# Table structure for table 'counters'
#

CREATE TABLE counters (
  name varchar(254) NOT NULL default '',
  the_number decimal(19,0) NOT NULL default '0',
  oid decimal(19,0) NOT NULL default '0',
  version int(11) NOT NULL default '0',
  PRIMARY KEY  (oid),
  UNIQUE KEY I1_Counters (name)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;



#
# Dumping data for table 'counters'
#



#
# Table structure for table 'createprocesseventaudits'
#

CREATE TABLE createprocesseventaudits (
  UTCTime varchar(254) NOT NULL default '',
  TheType decimal(19,0) NOT NULL default '0',
  ProcessId varchar(254) NOT NULL default '',
  ProcessName varchar(254) default NULL,
  ProcessDefinitionName varchar(254) NOT NULL default '',
  ProcessDefinitionVersion varchar(20) NOT NULL default '',
  ProcessDefinitionId varchar(100) NOT NULL default '',
  PackageId varchar(100) NOT NULL default '',
  PActivityId varchar(254) default NULL,
  PProcessId varchar(254) default NULL,
  PProcessName varchar(254) default NULL,
  PProcessDefinitionName varchar(254) default NULL,
  PProcessDefinitionVersion varchar(20) default NULL,
  PActivityDefinitionId varchar(100) default NULL,
  PActivitySetDefinitionId varchar(100) default NULL,
  PProcessDefinitionId varchar(100) default NULL,
  PPackageId varchar(100) default NULL,
  CNT decimal(19,0) NOT NULL default '0',
  oid decimal(19,0) NOT NULL default '0',
  version int(11) NOT NULL default '0',
  PRIMARY KEY  (oid),
  UNIQUE KEY I1_CreateProcessEventAudits (CNT)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;



#
# Dumping data for table 'createprocesseventaudits'
#



#
# Table structure for table 'dataeventaudits'
#

CREATE TABLE dataeventaudits (
  UTCTime varchar(254) NOT NULL default '',
  TheType decimal(19,0) NOT NULL default '0',
  ActivityId varchar(254) default NULL,
  ActivityName varchar(254) default NULL,
  ProcessId varchar(254) NOT NULL default '',
  ProcessName varchar(254) default NULL,
  ProcessDefinitionName varchar(254) NOT NULL default '',
  ProcessDefinitionVersion varchar(20) NOT NULL default '',
  ActivityDefinitionId varchar(100) default NULL,
  ActivitySetDefinitionId varchar(100) default NULL,
  ProcessDefinitionId varchar(100) NOT NULL default '',
  PackageId varchar(100) NOT NULL default '',
  CNT decimal(19,0) NOT NULL default '0',
  oid decimal(19,0) NOT NULL default '0',
  version int(11) NOT NULL default '0',
  PRIMARY KEY  (oid),
  UNIQUE KEY I1_DataEventAudits (CNT)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;



#
# Dumping data for table 'dataeventaudits'
#



#
# Table structure for table 'deadlines'
#

CREATE TABLE deadlines (
  Process decimal(19,0) NOT NULL default '0',
  Activity decimal(19,0) NOT NULL default '0',
  CNT decimal(19,0) NOT NULL default '0',
  TimeLimit double NOT NULL default '0',
  ExceptionName varchar(254) NOT NULL default '',
  IsSynchronous smallint(6) NOT NULL default '0',
  oid decimal(19,0) NOT NULL default '0',
  version int(11) NOT NULL default '0',
  PRIMARY KEY  (oid),
  UNIQUE KEY I1_Deadlines (CNT),
  KEY I2_Deadlines (Activity),
  KEY I3_Deadlines (Process)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;



#
# Dumping data for table 'deadlines'
#



#
# Table structure for table 'eventtypes'
#

CREATE TABLE eventtypes (
  KeyValue varchar(30) NOT NULL default '',
  Name varchar(50) NOT NULL default '',
  oid decimal(19,0) NOT NULL default '0',
  version int(11) NOT NULL default '0',
  PRIMARY KEY  (oid),
  UNIQUE KEY I1_EventTypes (KeyValue),
  UNIQUE KEY I2_EventTypes (Name)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;



#
# Dumping data for table 'eventtypes'
#

INSERT INTO eventtypes (KeyValue, Name, oid, version) VALUES("packageLoaded", "packageLoaded", "1000024", "0");
INSERT INTO eventtypes (KeyValue, Name, oid, version) VALUES("packageUnloaded", "packageUnloaded", "1000025", "0");
INSERT INTO eventtypes (KeyValue, Name, oid, version) VALUES("packageUpdated", "packageUpdated", "1000026", "0");
INSERT INTO eventtypes (KeyValue, Name, oid, version) VALUES("processCreated", "processCreated", "1000027", "0");
INSERT INTO eventtypes (KeyValue, Name, oid, version) VALUES("processStateChanged", "processStateChanged", "1000028", "0");
INSERT INTO eventtypes (KeyValue, Name, oid, version) VALUES("processContextChanged", "processContextChanged", "1000029", "0");
INSERT INTO eventtypes (KeyValue, Name, oid, version) VALUES("activityStateChanged", "activityStateChanged", "1000030", "0");
INSERT INTO eventtypes (KeyValue, Name, oid, version) VALUES("activityContextChanged", "activityContextChanged", "1000031", "0");
INSERT INTO eventtypes (KeyValue, Name, oid, version) VALUES("activityResultChanged", "activityResultChanged", "1000032", "0");
INSERT INTO eventtypes (KeyValue, Name, oid, version) VALUES("activityAssignmentChanged", "activityAssignmentChanged", "1000033", "0");


#
# Table structure for table 'formfieldmappings'
#

CREATE TABLE formfieldmappings (
  PACKAGE_ID varchar(100) NOT NULL default '',
  PROCESS_DEFINITION_ID varchar(100) default NULL,
  ACTIVITY_ID varchar(100) default NULL,
  WORKFLOW_ATTRIBUTENAME varchar(100) default NULL,
  FORM_ID varchar(100) default NULL,
  FIELD_ID varchar(100) default NULL,
  MODE_TYPE varchar(5) default NULL,
  oid varchar(100) NOT NULL default '',
  version int(11) default NULL,
  PRIMARY KEY  (oid)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;



#
# Dumping data for table 'formfieldmappings'
#



#
# Table structure for table 'formrolemappings'
#

CREATE TABLE formrolemappings (
  PACKAGE_ID varchar(100) NOT NULL default '',
  PROCESS_DEFINITION_ID varchar(100) default NULL,
  ACTIVITY_ID varchar(100) default NULL,
  WORKFLOW_PARTICIPANTID varchar(100) default NULL,
  FORM_ID varchar(100) default NULL,
  ROLE_ID varchar(100) default NULL,
  MODE_TYPE varchar(5) default NULL,
  OID varchar(100) NOT NULL default '',
  VERSION int(11) default NULL,
  PRIMARY KEY  (OID)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;



#
# Dumping data for table 'formrolemappings'
#



#
# Table structure for table 'geverassignment'
#

CREATE TABLE geverassignment (
  PROCESS_ID varchar(100) NOT NULL default '',
  ACTIVITY_ID varchar(100) NOT NULL default '',
  USERNAME varchar(50) NOT NULL default '',
  COMPLETION_DATE date default NULL,
  oid varchar(100) NOT NULL default '',
  version int(11) default NULL,
  PRIMARY KEY  (oid)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;



#
# Dumping data for table 'geverassignment'
#



#
# Table structure for table 'groupgrouptable'
#

CREATE TABLE groupgrouptable (
  sub_gid decimal(19,0) NOT NULL default '0',
  groupid decimal(19,0) NOT NULL default '0',
  oid decimal(19,0) NOT NULL default '0',
  version int(11) NOT NULL default '0',
  PRIMARY KEY  (oid),
  UNIQUE KEY I1_GroupGroupTable (sub_gid,groupid),
  KEY I2_GroupGroupTable (groupid)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;



#
# Dumping data for table 'groupgrouptable'
#



#
# Table structure for table 'grouptable'
#

CREATE TABLE grouptable (
  groupid varchar(125) NOT NULL default '',
  description varchar(254) default NULL,
  oid decimal(19,0) NOT NULL default '0',
  version int(11) NOT NULL default '0',
  PRIMARY KEY  (oid),
  UNIQUE KEY I1_GroupTable (groupid)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;



#
# Dumping data for table 'grouptable'
#



#
# Table structure for table 'groupuser'
#

CREATE TABLE groupuser (
  USERNAME varchar(100) NOT NULL default '',
  oid decimal(19,0) NOT NULL default '0',
  version int(11) NOT NULL default '0',
  PRIMARY KEY  (oid),
  UNIQUE KEY I1_GroupUser (USERNAME)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;



#
# Dumping data for table 'groupuser'
#



#
# Table structure for table 'groupuserpacklevelparticipant'
#

CREATE TABLE groupuserpacklevelparticipant (
  PARTICIPANTOID decimal(19,0) NOT NULL default '0',
  USEROID decimal(19,0) NOT NULL default '0',
  oid decimal(19,0) NOT NULL default '0',
  version int(11) NOT NULL default '0',
  PRIMARY KEY  (oid),
  UNIQUE KEY I1_GroupUserPackLevelParticipant (PARTICIPANTOID,USEROID)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;



#
# Dumping data for table 'groupuserpacklevelparticipant'
#



#
# Table structure for table 'groupuserproclevelparticipant'
#

CREATE TABLE groupuserproclevelparticipant (
  PARTICIPANTOID decimal(19,0) NOT NULL default '0',
  USEROID decimal(19,0) NOT NULL default '0',
  oid decimal(19,0) NOT NULL default '0',
  version int(11) NOT NULL default '0',
  PRIMARY KEY  (oid),
  UNIQUE KEY I1_GroupUserProcLevelParticipant (PARTICIPANTOID,USEROID)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;



#
# Dumping data for table 'groupuserproclevelparticipant'
#



#
# Table structure for table 'locktable'
#

CREATE TABLE locktable (
  EngineName varchar(254) NOT NULL default '',
  Id varchar(254) NOT NULL default '',
  oid decimal(19,0) NOT NULL default '0',
  version int(11) NOT NULL default '0',
  PRIMARY KEY  (oid),
  UNIQUE KEY I1_LockTable (Id)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;



#
# Dumping data for table 'locktable'
#



#
# Table structure for table 'neweventauditdata'
#

CREATE TABLE neweventauditdata (
  DataEventAudit decimal(19,0) NOT NULL default '0',
  VariableDefinitionId varchar(254) NOT NULL default '',
  VariableValue mediumblob NOT NULL,
  oid decimal(19,0) NOT NULL default '0',
  version int(11) NOT NULL default '0',
  PRIMARY KEY  (oid),
  UNIQUE KEY I1_NewEventAuditData (DataEventAudit,VariableDefinitionId)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;



#
# Dumping data for table 'neweventauditdata'
#



#
# Table structure for table 'nextxpdlversions'
#

CREATE TABLE nextxpdlversions (
  XPDLId varchar(100) NOT NULL default '',
  NextVersion varchar(20) NOT NULL default '',
  oid decimal(19,0) NOT NULL default '0',
  version int(11) NOT NULL default '0',
  PRIMARY KEY  (oid),
  UNIQUE KEY I1_NextXPDLVersions (XPDLId,NextVersion)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;



#
# Dumping data for table 'nextxpdlversions'
#



#
# Table structure for table 'normaluser'
#

CREATE TABLE normaluser (
  USERNAME varchar(100) NOT NULL default '',
  oid decimal(19,0) NOT NULL default '0',
  version int(11) NOT NULL default '0',
  PRIMARY KEY  (oid),
  UNIQUE KEY I1_NormalUser (USERNAME)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;



#
# Dumping data for table 'normaluser'
#



#
# Table structure for table 'objectid'
#

CREATE TABLE objectid (
  next decimal(19,0) NOT NULL default '0',
  PRIMARY KEY  (next)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;



#
# Dumping data for table 'objectid'
#

INSERT INTO objectid (next) VALUES("1000600");


#
# Table structure for table 'oldeventauditdata'
#

CREATE TABLE oldeventauditdata (
  DataEventAudit decimal(19,0) NOT NULL default '0',
  VariableDefinitionId varchar(254) NOT NULL default '',
  VariableValue mediumblob NOT NULL,
  oid decimal(19,0) NOT NULL default '0',
  version int(11) NOT NULL default '0',
  PRIMARY KEY  (oid),
  UNIQUE KEY I1_OldEventAuditData (DataEventAudit,VariableDefinitionId)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;



#
# Dumping data for table 'oldeventauditdata'
#



#
# Table structure for table 'packlevelparticipant'
#

CREATE TABLE packlevelparticipant (
  PARTICIPANT_ID varchar(100) NOT NULL default '',
  PACKAGEOID decimal(19,0) NOT NULL default '0',
  oid decimal(19,0) NOT NULL default '0',
  version int(11) NOT NULL default '0',
  PRIMARY KEY  (oid),
  UNIQUE KEY I1_PackLevelParticipant (PARTICIPANT_ID,PACKAGEOID)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;



#
# Dumping data for table 'packlevelparticipant'
#



#
# Table structure for table 'packlevelxpdlapp'
#

CREATE TABLE packlevelxpdlapp (
  APPLICATION_ID varchar(100) NOT NULL default '',
  PACKAGEOID decimal(19,0) NOT NULL default '0',
  oid decimal(19,0) NOT NULL default '0',
  version int(11) NOT NULL default '0',
  PRIMARY KEY  (oid),
  UNIQUE KEY I1_PackLevelXPDLApp (APPLICATION_ID,PACKAGEOID)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;



#
# Dumping data for table 'packlevelxpdlapp'
#



#
# Table structure for table 'packlevelxpdlapptaappdetail'
#

CREATE TABLE packlevelxpdlapptaappdetail (
  XPDL_APPOID decimal(19,0) NOT NULL default '0',
  TOOLAGENTOID decimal(19,0) NOT NULL default '0',
  oid decimal(19,0) NOT NULL default '0',
  version int(11) NOT NULL default '0',
  PRIMARY KEY  (oid),
  UNIQUE KEY I1_PackLevelXPDLAppTAAppDetail (XPDL_APPOID,TOOLAGENTOID)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;



#
# Dumping data for table 'packlevelxpdlapptaappdetail'
#



#
# Table structure for table 'packlevelxpdlapptaappdetailusr'
#

CREATE TABLE packlevelxpdlapptaappdetailusr (
  XPDL_APPOID decimal(19,0) NOT NULL default '0',
  TOOLAGENTOID decimal(19,0) NOT NULL default '0',
  oid decimal(19,0) NOT NULL default '0',
  version int(11) NOT NULL default '0',
  PRIMARY KEY  (oid),
  UNIQUE KEY I1_PackLevelXPDLAppTAAppDetailUsr (XPDL_APPOID,TOOLAGENTOID)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;



#
# Dumping data for table 'packlevelxpdlapptaappdetailusr'
#



#
# Table structure for table 'packlevelxpdlapptaappuser'
#

CREATE TABLE packlevelxpdlapptaappuser (
  XPDL_APPOID decimal(19,0) NOT NULL default '0',
  TOOLAGENTOID decimal(19,0) NOT NULL default '0',
  oid decimal(19,0) NOT NULL default '0',
  version int(11) NOT NULL default '0',
  PRIMARY KEY  (oid),
  UNIQUE KEY I1_PackLevelXPDLAppTAAppUser (XPDL_APPOID,TOOLAGENTOID)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;



#
# Dumping data for table 'packlevelxpdlapptaappuser'
#



#
# Table structure for table 'packlevelxpdlapptoolagentapp'
#

CREATE TABLE packlevelxpdlapptoolagentapp (
  XPDL_APPOID decimal(19,0) NOT NULL default '0',
  TOOLAGENTOID decimal(19,0) NOT NULL default '0',
  oid decimal(19,0) NOT NULL default '0',
  version int(11) NOT NULL default '0',
  PRIMARY KEY  (oid),
  UNIQUE KEY I1_PackLevelXPDLAppToolAgentApp (XPDL_APPOID,TOOLAGENTOID)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;



#
# Dumping data for table 'packlevelxpdlapptoolagentapp'
#



#
# Table structure for table 'processdata'
#

CREATE TABLE processdata (
  Process decimal(19,0) NOT NULL default '0',
  VariableDefinitionId varchar(254) NOT NULL default '',
  VariableValue mediumblob NOT NULL,
  oid decimal(19,0) NOT NULL default '0',
  version int(11) NOT NULL default '0',
  PRIMARY KEY  (oid),
  UNIQUE KEY I1_ProcessData (Process,VariableDefinitionId),
  KEY I2_ProcessData (Process)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;



#
# Dumping data for table 'processdata'
#



#
# Table structure for table 'processdefinitions'
#

CREATE TABLE processdefinitions (
  Name varchar(254) NOT NULL default '',
  PackageId varchar(100) NOT NULL default '',
  ProcessDefinitionId varchar(100) NOT NULL default '',
  ProcessDefinitionCreated varchar(254) NOT NULL default '',
  ProcessDefinitionVersion varchar(20) NOT NULL default '',
  State int(11) NOT NULL default '0',
  oid decimal(19,0) NOT NULL default '0',
  version int(11) NOT NULL default '0',
  PRIMARY KEY  (oid),
  UNIQUE KEY I1_ProcessDefinitions (Name)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;



#
# Dumping data for table 'processdefinitions'
#



#
# Table structure for table 'processes'
#

CREATE TABLE processes (
  Id varchar(254) NOT NULL default '',
  ProcessDefinition decimal(19,0) NOT NULL default '0',
  State decimal(19,0) NOT NULL default '0',
  Priority int(11) default NULL,
  Name varchar(254) default NULL,
  Started double default NULL,
  LastStateTime double default NULL,
  Description varchar(254) default NULL,
  oid decimal(19,0) NOT NULL default '0',
  version int(11) NOT NULL default '0',
  PRIMARY KEY  (oid),
  UNIQUE KEY I1_Processes (Id),
  KEY I2_Processes (ProcessDefinition)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;



#
# Dumping data for table 'processes'
#



#
# Table structure for table 'processrequesters'
#

CREATE TABLE processrequesters (
  Id varchar(254) NOT NULL default '',
  ActivityRequester decimal(19,0) default NULL,
  ResourceRequester decimal(19,0) default NULL,
  oid decimal(19,0) NOT NULL default '0',
  version int(11) NOT NULL default '0',
  PRIMARY KEY  (oid),
  UNIQUE KEY I1_ProcessRequesters (Id),
  KEY I2_ProcessRequesters (ActivityRequester),
  KEY I3_ProcessRequesters (ResourceRequester)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;



#
# Dumping data for table 'processrequesters'
#



#
# Table structure for table 'processstateeventaudits'
#

CREATE TABLE processstateeventaudits (
  KeyValue varchar(30) NOT NULL default '',
  Name varchar(50) NOT NULL default '',
  oid decimal(19,0) NOT NULL default '0',
  version int(11) NOT NULL default '0',
  PRIMARY KEY  (oid),
  UNIQUE KEY I1_ProcessStateEventAudits (KeyValue),
  UNIQUE KEY I2_ProcessStateEventAudits (Name)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;



#
# Dumping data for table 'processstateeventaudits'
#

INSERT INTO processstateeventaudits (KeyValue, Name, oid, version) VALUES("open.running", "open.running", "1000012", "0");
INSERT INTO processstateeventaudits (KeyValue, Name, oid, version) VALUES("open.not_running.not_started", "open.not_running.not_started", "1000014", "0");
INSERT INTO processstateeventaudits (KeyValue, Name, oid, version) VALUES("open.not_running.suspended", "open.not_running.suspended", "1000016", "0");
INSERT INTO processstateeventaudits (KeyValue, Name, oid, version) VALUES("closed.completed", "closed.completed", "1000018", "0");
INSERT INTO processstateeventaudits (KeyValue, Name, oid, version) VALUES("closed.terminated", "closed.terminated", "1000020", "0");
INSERT INTO processstateeventaudits (KeyValue, Name, oid, version) VALUES("closed.aborted", "closed.aborted", "1000022", "0");


#
# Table structure for table 'processstates'
#

CREATE TABLE processstates (
  KeyValue varchar(30) NOT NULL default '',
  Name varchar(50) NOT NULL default '',
  oid decimal(19,0) NOT NULL default '0',
  version int(11) NOT NULL default '0',
  PRIMARY KEY  (oid),
  UNIQUE KEY I1_ProcessStates (KeyValue),
  UNIQUE KEY I2_ProcessStates (Name)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;



#
# Dumping data for table 'processstates'
#

INSERT INTO processstates (KeyValue, Name, oid, version) VALUES("open.running", "open.running", "1000000", "0");
INSERT INTO processstates (KeyValue, Name, oid, version) VALUES("open.not_running.not_started", "open.not_running.not_started", "1000002", "0");
INSERT INTO processstates (KeyValue, Name, oid, version) VALUES("open.not_running.suspended", "open.not_running.suspended", "1000004", "0");
INSERT INTO processstates (KeyValue, Name, oid, version) VALUES("closed.completed", "closed.completed", "1000006", "0");
INSERT INTO processstates (KeyValue, Name, oid, version) VALUES("closed.terminated", "closed.terminated", "1000008", "0");
INSERT INTO processstates (KeyValue, Name, oid, version) VALUES("closed.aborted", "closed.aborted", "1000010", "0");


#
# Table structure for table 'proclevelparticipant'
#

CREATE TABLE proclevelparticipant (
  PARTICIPANT_ID varchar(100) NOT NULL default '',
  PROCESSOID decimal(19,0) NOT NULL default '0',
  oid decimal(19,0) NOT NULL default '0',
  version int(11) NOT NULL default '0',
  PRIMARY KEY  (oid),
  UNIQUE KEY I1_ProcLevelParticipant (PARTICIPANT_ID,PROCESSOID)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;



#
# Dumping data for table 'proclevelparticipant'
#



#
# Table structure for table 'proclevelxpdlapp'
#

CREATE TABLE proclevelxpdlapp (
  APPLICATION_ID varchar(100) NOT NULL default '',
  PROCESSOID decimal(19,0) NOT NULL default '0',
  oid decimal(19,0) NOT NULL default '0',
  version int(11) NOT NULL default '0',
  PRIMARY KEY  (oid),
  UNIQUE KEY I1_ProcLevelXPDLApp (APPLICATION_ID,PROCESSOID)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;



#
# Dumping data for table 'proclevelxpdlapp'
#



#
# Table structure for table 'proclevelxpdlapptaappdetail'
#

CREATE TABLE proclevelxpdlapptaappdetail (
  XPDL_APPOID decimal(19,0) NOT NULL default '0',
  TOOLAGENTOID decimal(19,0) NOT NULL default '0',
  oid decimal(19,0) NOT NULL default '0',
  version int(11) NOT NULL default '0',
  PRIMARY KEY  (oid),
  UNIQUE KEY I1_ProcLevelXPDLAppTAAppDetail (XPDL_APPOID,TOOLAGENTOID)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;



#
# Dumping data for table 'proclevelxpdlapptaappdetail'
#



#
# Table structure for table 'proclevelxpdlapptaappdetailusr'
#

CREATE TABLE proclevelxpdlapptaappdetailusr (
  XPDL_APPOID decimal(19,0) NOT NULL default '0',
  TOOLAGENTOID decimal(19,0) NOT NULL default '0',
  oid decimal(19,0) NOT NULL default '0',
  version int(11) NOT NULL default '0',
  PRIMARY KEY  (oid),
  UNIQUE KEY I1_ProcLevelXPDLAppTAAppDetailUsr (XPDL_APPOID,TOOLAGENTOID)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;



#
# Dumping data for table 'proclevelxpdlapptaappdetailusr'
#



#
# Table structure for table 'proclevelxpdlapptaappuser'
#

CREATE TABLE proclevelxpdlapptaappuser (
  XPDL_APPOID decimal(19,0) NOT NULL default '0',
  TOOLAGENTOID decimal(19,0) NOT NULL default '0',
  oid decimal(19,0) NOT NULL default '0',
  version int(11) NOT NULL default '0',
  PRIMARY KEY  (oid),
  UNIQUE KEY I1_ProcLevelXPDLAppTAAppUser (XPDL_APPOID,TOOLAGENTOID)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;



#
# Dumping data for table 'proclevelxpdlapptaappuser'
#



#
# Table structure for table 'proclevelxpdlapptoolagentapp'
#

CREATE TABLE proclevelxpdlapptoolagentapp (
  XPDL_APPOID decimal(19,0) NOT NULL default '0',
  TOOLAGENTOID decimal(19,0) NOT NULL default '0',
  oid decimal(19,0) NOT NULL default '0',
  version int(11) NOT NULL default '0',
  PRIMARY KEY  (oid),
  UNIQUE KEY I1_ProcLevelXPDLAppToolAgentApp (XPDL_APPOID,TOOLAGENTOID)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;



#
# Dumping data for table 'proclevelxpdlapptoolagentapp'
#



#
# Table structure for table 'resourcestable'
#

CREATE TABLE resourcestable (
  Username varchar(125) NOT NULL default '',
  Name varchar(100) default NULL,
  oid decimal(19,0) NOT NULL default '0',
  version int(11) NOT NULL default '0',
  PRIMARY KEY  (oid),
  UNIQUE KEY I1_ResourcesTable (Username)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;



#
# Dumping data for table 'resourcestable'
#

INSERT INTO resourcestable (Username, Name, oid, version) VALUES("admin", NULL, "1000201", "0");


#
# Table structure for table 'sharkid'
#

CREATE TABLE sharkid (
  idType int(11) NOT NULL default '0',
  id int(11) NOT NULL default '0',
  PRIMARY KEY  (idType)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;



#
# Dumping data for table 'sharkid'
#



#
# Table structure for table 'stateeventaudits'
#

CREATE TABLE stateeventaudits (
  UTCTime varchar(254) NOT NULL default '',
  TheType decimal(19,0) NOT NULL default '0',
  ActivityId varchar(254) default NULL,
  ActivityName varchar(254) default NULL,
  ProcessId varchar(254) NOT NULL default '',
  ProcessName varchar(254) default NULL,
  ProcessDefinitionName varchar(100) NOT NULL default '',
  ProcessDefinitionVersion varchar(20) NOT NULL default '',
  ActivityDefinitionId varchar(100) default NULL,
  ActivitySetDefinitionId varchar(100) default NULL,
  ProcessDefinitionId varchar(100) NOT NULL default '',
  PackageId varchar(100) NOT NULL default '',
  OldProcessState decimal(19,0) default NULL,
  NewProcessState decimal(19,0) default NULL,
  OldActivityState decimal(19,0) default NULL,
  NewActivityState decimal(19,0) default NULL,
  CNT decimal(19,0) NOT NULL default '0',
  oid decimal(19,0) NOT NULL default '0',
  version int(11) NOT NULL default '0',
  PRIMARY KEY  (oid),
  UNIQUE KEY I1_StateEventAudits (CNT)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;



#
# Dumping data for table 'stateeventaudits'
#



#
# Table structure for table 'toolagentapp'
#

CREATE TABLE toolagentapp (
  TOOL_AGENT_NAME varchar(250) NOT NULL default '',
  APP_NAME varchar(250) NOT NULL default '',
  oid decimal(19,0) NOT NULL default '0',
  version int(11) NOT NULL default '0',
  PRIMARY KEY  (oid),
  UNIQUE KEY I1_ToolAgentApp (TOOL_AGENT_NAME,APP_NAME)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;



#
# Dumping data for table 'toolagentapp'
#



#
# Table structure for table 'toolagentappdetail'
#

CREATE TABLE toolagentappdetail (
  APP_MODE decimal(10,0) NOT NULL default '0',
  TOOLAGENT_APPOID decimal(19,0) NOT NULL default '0',
  oid decimal(19,0) NOT NULL default '0',
  version int(11) NOT NULL default '0',
  PRIMARY KEY  (oid),
  UNIQUE KEY I1_ToolAgentAppDetail (APP_MODE,TOOLAGENT_APPOID)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;



#
# Dumping data for table 'toolagentappdetail'
#



#
# Table structure for table 'toolagentappdetailuser'
#

CREATE TABLE toolagentappdetailuser (
  TOOLAGENT_APPOID decimal(19,0) NOT NULL default '0',
  USEROID decimal(19,0) NOT NULL default '0',
  oid decimal(19,0) NOT NULL default '0',
  version int(11) NOT NULL default '0',
  PRIMARY KEY  (oid),
  UNIQUE KEY I1_ToolAgentAppDetailUser (TOOLAGENT_APPOID,USEROID)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;



#
# Dumping data for table 'toolagentappdetailuser'
#



#
# Table structure for table 'toolagentappuser'
#

CREATE TABLE toolagentappuser (
  TOOLAGENT_APPOID decimal(19,0) NOT NULL default '0',
  USEROID decimal(19,0) NOT NULL default '0',
  oid decimal(19,0) NOT NULL default '0',
  version int(11) NOT NULL default '0',
  PRIMARY KEY  (oid),
  UNIQUE KEY I1_ToolAgentAppUser (TOOLAGENT_APPOID,USEROID)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;



#
# Dumping data for table 'toolagentappuser'
#



#
# Table structure for table 'toolagentuser'
#

CREATE TABLE toolagentuser (
  USERNAME varchar(100) NOT NULL default '',
  PWD varchar(100) default NULL,
  oid decimal(19,0) NOT NULL default '0',
  version int(11) NOT NULL default '0',
  PRIMARY KEY  (oid),
  UNIQUE KEY I1_ToolAgentUser (USERNAME)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;



#
# Dumping data for table 'toolagentuser'
#



#
# Table structure for table 'usergrouptable'
#

CREATE TABLE usergrouptable (
  userid decimal(19,0) NOT NULL default '0',
  groupid decimal(19,0) NOT NULL default '0',
  oid decimal(19,0) NOT NULL default '0',
  version int(11) NOT NULL default '0',
  PRIMARY KEY  (oid),
  UNIQUE KEY I1_UserGroupTable (userid,groupid)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;



#
# Dumping data for table 'usergrouptable'
#



#
# Table structure for table 'userpacklevelparticipant'
#

CREATE TABLE userpacklevelparticipant (
  PARTICIPANTOID decimal(19,0) NOT NULL default '0',
  USEROID decimal(19,0) NOT NULL default '0',
  oid decimal(19,0) NOT NULL default '0',
  version int(11) NOT NULL default '0',
  PRIMARY KEY  (oid),
  UNIQUE KEY I1_UserPackLevelParticipant (PARTICIPANTOID,USEROID)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;



#
# Dumping data for table 'userpacklevelparticipant'
#



#
# Table structure for table 'userproclevelparticipant'
#

CREATE TABLE userproclevelparticipant (
  PARTICIPANTOID decimal(19,0) NOT NULL default '0',
  USEROID decimal(19,0) NOT NULL default '0',
  oid decimal(19,0) NOT NULL default '0',
  version int(11) NOT NULL default '0',
  PRIMARY KEY  (oid),
  UNIQUE KEY I1_UserProcLevelParticipant (PARTICIPANTOID,USEROID)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;



#
# Dumping data for table 'userproclevelparticipant'
#



#
# Table structure for table 'usertable'
#

CREATE TABLE usertable (
  userid varchar(125) NOT NULL default '',
  firstname varchar(50) default NULL,
  lastname varchar(50) default NULL,
  passwd varchar(50) NOT NULL default '',
  email varchar(254) default NULL,
  oid decimal(19,0) NOT NULL default '0',
  version int(11) NOT NULL default '0',
  PRIMARY KEY  (oid),
  UNIQUE KEY I1_UserTable (userid)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;



#
# Dumping data for table 'usertable'
#



#
# Table structure for table 'xpdlapplicationpackage'
#

CREATE TABLE xpdlapplicationpackage (
  PACKAGE_ID varchar(100) NOT NULL default '',
  oid decimal(19,0) NOT NULL default '0',
  version int(11) NOT NULL default '0',
  PRIMARY KEY  (oid),
  UNIQUE KEY I1_XPDLApplicationPackage (PACKAGE_ID)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;



#
# Dumping data for table 'xpdlapplicationpackage'
#



#
# Table structure for table 'xpdlapplicationprocess'
#

CREATE TABLE xpdlapplicationprocess (
  PROCESS_ID varchar(100) NOT NULL default '',
  PACKAGEOID decimal(19,0) NOT NULL default '0',
  oid decimal(19,0) NOT NULL default '0',
  version int(11) NOT NULL default '0',
  PRIMARY KEY  (oid),
  UNIQUE KEY I1_XPDLApplicationProcess (PROCESS_ID,PACKAGEOID)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;



#
# Dumping data for table 'xpdlapplicationprocess'
#



#
# Table structure for table 'xpdldata'
#

CREATE TABLE xpdldata (
  XPDLContent mediumblob NOT NULL,
  XPDL decimal(19,0) NOT NULL default '0',
  CNT decimal(19,0) NOT NULL default '0',
  oid decimal(19,0) NOT NULL default '0',
  version int(11) NOT NULL default '0',
  PRIMARY KEY  (oid),
  UNIQUE KEY I1_XPDLData (CNT)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;



#
# Dumping data for table 'xpdldata'
#



#
# Table structure for table 'xpdlhistory'
#

CREATE TABLE xpdlhistory (
  XPDLId varchar(100) NOT NULL default '',
  XPDLVersion varchar(20) NOT NULL default '',
  oid decimal(19,0) NOT NULL default '0',
  version int(11) NOT NULL default '0',
  PRIMARY KEY  (oid),
  UNIQUE KEY I1_XPDLHistory (XPDLId,XPDLVersion)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;



#
# Dumping data for table 'xpdlhistory'
#



#
# Table structure for table 'xpdlhistorydata'
#

CREATE TABLE xpdlhistorydata (
  XPDLContent mediumblob NOT NULL,
  XPDLHistory decimal(19,0) NOT NULL default '0',
  CNT decimal(19,0) NOT NULL default '0',
  oid decimal(19,0) NOT NULL default '0',
  version int(11) NOT NULL default '0',
  PRIMARY KEY  (oid),
  UNIQUE KEY I1_XPDLHistoryData (CNT)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;



#
# Dumping data for table 'xpdlhistorydata'
#



#
# Table structure for table 'xpdlparticipantpackage'
#

CREATE TABLE xpdlparticipantpackage (
  PACKAGE_ID varchar(100) NOT NULL default '',
  oid decimal(19,0) NOT NULL default '0',
  version int(11) NOT NULL default '0',
  PRIMARY KEY  (oid),
  UNIQUE KEY I1_XPDLParticipantPackage (PACKAGE_ID)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;



#
# Dumping data for table 'xpdlparticipantpackage'
#



#
# Table structure for table 'xpdlparticipantprocess'
#

CREATE TABLE xpdlparticipantprocess (
  PROCESS_ID varchar(100) NOT NULL default '',
  PACKAGEOID decimal(19,0) NOT NULL default '0',
  oid decimal(19,0) NOT NULL default '0',
  version int(11) NOT NULL default '0',
  PRIMARY KEY  (oid),
  UNIQUE KEY I1_XPDLParticipantProcess (PROCESS_ID,PACKAGEOID)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;



#
# Dumping data for table 'xpdlparticipantprocess'
#



#
# Table structure for table 'xpdlreferences'
#

CREATE TABLE xpdlreferences (
  ReferredXPDLId varchar(100) NOT NULL default '',
  ReferringXPDL decimal(19,0) NOT NULL default '0',
  oid decimal(19,0) NOT NULL default '0',
  version int(11) NOT NULL default '0',
  PRIMARY KEY  (oid),
  UNIQUE KEY I1_XPDLReferences (ReferredXPDLId,ReferringXPDL)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;



#
# Dumping data for table 'xpdlreferences'
#



#
# Table structure for table 'xpdls'
#

CREATE TABLE xpdls (
  XPDLId varchar(100) NOT NULL default '',
  XPDLVersion varchar(20) NOT NULL default '',
  oid decimal(19,0) NOT NULL default '0',
  version int(11) NOT NULL default '0',
  PRIMARY KEY  (oid),
  UNIQUE KEY I1_XPDLS (XPDLId,XPDLVersion)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;



#
# Dumping data for table 'xpdls'
#

