
CREATE TABLE State (
                StateId CHAR(2) NOT NULL,
                StateName VARCHAR(20) NOT NULL,
                StateNumber INT NOT NULL,
                PRIMARY KEY (StateId)
);


CREATE TABLE County (
                CountyId CHAR(2) NOT NULL,
                CountyName VARCHAR(20) NOT NULL,
                StateId CHAR(2) NOT NULL,
                PRIMARY KEY (CountyId)
);


CREATE TABLE SiteInfo (
                SiteInfoId INT AUTO_INCREMENT NOT NULL,
                SiteNumber INT NOT NULL,
                CountyId CHAR(2) NOT NULL,
                ProjectNumber VARCHAR(15) NOT NULL,
                AccessionNumber VARCHAR(15) NOT NULL,
                PRIMARY KEY (SiteInfoId)
);


CREATE TABLE Unit (
                UnitId INT AUTO_INCREMENT NOT NULL,
                FieldSiteNumber INT NOT NULL,
                AreaLetter VARCHAR(1) NOT NULL,
                UnitNumber INT NOT NULL,
                StartDepth INT NOT NULL,
                EndDepth INT NOT NULL,
                SiteInfoId INT NOT NULL,
                PRIMARY KEY (UnitId)
);


CREATE TABLE ASN3 (
                ASN3Id INT AUTO_INCREMENT NOT NULL,
                ASN3Name VARCHAR(50) NOT NULL,
                PRIMARY KEY (ASN3Id)
);


CREATE TABLE ASN2 (
                ASN2Id INT AUTO_INCREMENT NOT NULL,
                ASN2Name VARCHAR(50) NOT NULL,
                PRIMARY KEY (ASN2Id)
);


CREATE TABLE LSN (
                LSNId INT AUTO_INCREMENT NOT NULL,
                LSNName VARCHAR(50) NOT NULL,
                PRIMARY KEY (LSNId)
);


CREATE TABLE ASN1 (
                ASN1Id INT AUTO_INCREMENT NOT NULL,
                ASN1Name VARCHAR(50) NOT NULL,
                PRIMARY KEY (ASN1Id)
);


CREATE TABLE Period (
                PeriodId INT AUTO_INCREMENT NOT NULL,
                PeriodType VARCHAR(20) NOT NULL,
                PRIMARY KEY (PeriodId)
);


CREATE TABLE LabTechnician (
                LabTechnicianId INT AUTO_INCREMENT NOT NULL,
                LabTechnicianName VARCHAR(50) NOT NULL,
                LabTechnicianInitials VARCHAR(3) NOT NULL,
                PRIMARY KEY (LabTechnicianId)
);


CREATE TABLE Artifact (
                ArtifactId INT AUTO_INCREMENT NOT NULL,
                UnitId INT NOT NULL,
                PeriodId INT NOT NULL,
                LSNId INT NOT NULL,
                ASN1Id INT NOT NULL,
                ASN2Id INT NOT NULL,
                ASN3Id INT,
                AdditionalDescription VARCHAR(2000),
                Quantity INT NOT NULL,
                Weight NUMERIC(5,1),
                LabTechnicianId INT NOT NULL,
                DateAnalyzed DATE NOT NULL,
                Notes VARCHAR(2000),
                ImageKey VARCHAR(50),
                Image LONGBLOB,
                PRIMARY KEY (ArtifactId)
);


ALTER TABLE County ADD CONSTRAINT state_county_fk
FOREIGN KEY (StateId)
REFERENCES State (StateId)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE SiteInfo ADD CONSTRAINT county_siteinfo_fk
FOREIGN KEY (CountyId)
REFERENCES County (CountyId)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE Unit ADD CONSTRAINT siteinfo_unit_fk
FOREIGN KEY (SiteInfoId)
REFERENCES SiteInfo (SiteInfoId)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE Artifact ADD CONSTRAINT unit_artifact_fk
FOREIGN KEY (UnitId)
REFERENCES Unit (UnitId)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE Artifact ADD CONSTRAINT asn3_artifact_fk
FOREIGN KEY (ASN3Id)
REFERENCES ASN3 (ASN3Id)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE Artifact ADD CONSTRAINT asn2_artifact_fk
FOREIGN KEY (ASN2Id)
REFERENCES ASN2 (ASN2Id)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE Artifact ADD CONSTRAINT lsn_artifact_fk
FOREIGN KEY (LSNId)
REFERENCES LSN (LSNId)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE Artifact ADD CONSTRAINT asn1_artifact_fk
FOREIGN KEY (ASN1Id)
REFERENCES ASN1 (ASN1Id)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE Artifact ADD CONSTRAINT period_artifact_fk
FOREIGN KEY (PeriodId)
REFERENCES Period (PeriodId)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE Artifact ADD CONSTRAINT employee_artifact_fk
FOREIGN KEY (LabTechnicianId)
REFERENCES LabTechnician (LabTechnicianId)
ON DELETE NO ACTION
ON UPDATE NO ACTION;
