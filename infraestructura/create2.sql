
CREATE DATABASE appDBCDonaPre;
GO

USE [appDBCDonaPre]

GO

CREATE SCHEMA siad;
GO
CREATE SCHEMA siadConstants;
GO
/****** Object:  Table [siad].[consulta]    Script Date: 31/07/2020 12:20:12 ******/

SET ANSI_NULLS ON

GO

SET QUOTED_IDENTIFIER ON

GO

CREATE TABLE [siad].[consulta](

	[codiConsulta] [int] NOT NULL,

	[CodiExpedient] [int] NULL,

	[datacon] [date] NULL,

	[ServeiAten] [nvarchar](255) NULL,

	[area] [nvarchar](255) NULL,

	[areaConsulta] [nvarchar](255) NULL,

	[Sense] [nvarchar](2) NULL,

	[Psicologic] [nvarchar](2) NULL,

	[Juridic] [nvarchar](2) NULL,

	[Generalitat] [nvarchar](2) NULL,

	[DerivaciGen] [nvarchar](255) NULL,

	[Altres] [nvarchar](2) NULL,

	[DerivacioAltres] [nvarchar](255) NULL,

	[usuari] [nvarchar](50) NULL,

	[dataalta] [datetime] NULL,

	[tramitacio] [nvarchar](255) NULL,

 CONSTRAINT [PK_consulta] PRIMARY KEY CLUSTERED 

(

	[codiConsulta] ASC

)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, FILLFACTOR = 20) ON [PRIMARY]

) ON [PRIMARY]

GO

/****** Object:  Table [siad].[solicitant]    Script Date: 31/07/2020 12:20:12 ******/

SET ANSI_NULLS ON

GO

SET QUOTED_IDENTIFIER ON

GO

CREATE TABLE [siad].[solicitant](

	[Siad] [nvarchar](255) NULL,

	[DataNaix] [date] NULL,

	[Telefon] [nvarchar](20) NULL,

	[CodiExpedient] [int] NOT NULL,

	[NomCognoms] [nvarchar](255) NULL,

	[Poblacio] [nvarchar](255) NULL,

	[Barri] [nvarchar](255) NULL,

	[CP] [nvarchar](5) NULL,

	[Comarca] [nvarchar](50) NULL,

	[Demarcacio] [nvarchar](255) NULL,

	[PaisOrigen] [nvarchar](255) NULL,

	[Sexe] [nvarchar](10) NULL,

	[Tramitacio] [nvarchar](255) NULL,

	[CanalEntrada] [nvarchar](255) NULL,

	[SituacioLaboral] [nvarchar](255) NULL,

	[EstatCivil] [nvarchar](50) NULL,

	[UnitatConvivencia] [nvarchar](255) NULL,

	[Professio] [nvarchar](255) NULL,

	[NivellFormacio] [nvarchar](255) NULL,

	[FormacioHomologada] [nvarchar](2) NULL,

	[SituacioEconomica] [nvarchar](255) NULL,

	[RepPrestacions] [nvarchar](2) NULL,

	[Prestacio] [nvarchar](50) NULL,

	[usuari] [nvarchar](50) NULL,

	[dataalta] [datetime] NULL,

	[nExpedient] [int] NULL,

	[anyExpedient] [int] NULL,

 CONSTRAINT [PK_solicitant] PRIMARY KEY CLUSTERED 

(

	[CodiExpedient] ASC

)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, FILLFACTOR = 20) ON [PRIMARY]

) ON [PRIMARY]

GO

/****** Object:  Table [siadConstants].[area]    Script Date: 31/07/2020 12:20:12 ******/

SET ANSI_NULLS ON

GO

SET QUOTED_IDENTIFIER ON

GO

CREATE TABLE [siadConstants].[area](

	[id] [bigint] IDENTITY(1,1) NOT NULL,

	[textArea] [nvarchar](300) NULL,

 CONSTRAINT [PK_area] PRIMARY KEY CLUSTERED 

(

	[id] ASC

)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, FILLFACTOR = 20) ON [PRIMARY]

) ON [PRIMARY]

GO

/****** Object:  Table [siadConstants].[areaConsulta]    Script Date: 31/07/2020 12:20:12 ******/

SET ANSI_NULLS ON

GO

SET QUOTED_IDENTIFIER ON

GO

CREATE TABLE [siadConstants].[areaConsulta](

	[id] [bigint] IDENTITY(1,1) NOT NULL,

	[textAreaConsulta] [nvarchar](300) NULL,

	[fkConsulta] [bigint] NULL,

 CONSTRAINT [PK_areaConsulta] PRIMARY KEY CLUSTERED 

(

	[id] ASC

)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, FILLFACTOR = 20) ON [PRIMARY]

) ON [PRIMARY]

GO

/****** Object:  Table [siadConstants].[ciutats]    Script Date: 31/07/2020 12:20:12 ******/

SET ANSI_NULLS ON

GO

SET QUOTED_IDENTIFIER ON

GO

CREATE TABLE [siadConstants].[ciutats](

	[nom] [char](80) NOT NULL,

	[codiProvincia] [int] NOT NULL,

	[cp] [char](5) NOT NULL,

	[codiCiutat] [int] NOT NULL,

 CONSTRAINT [PKSIAD_CIUTATS] PRIMARY KEY CLUSTERED 

(

	[codiCiutat] ASC

)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, FILLFACTOR = 20) ON [PRIMARY]

) ON [PRIMARY]

GO

/****** Object:  Table [siadConstants].[comarques]    Script Date: 31/07/2020 12:20:12 ******/

SET ANSI_NULLS ON

GO

SET QUOTED_IDENTIFIER ON

GO

CREATE TABLE [siadConstants].[comarques](

	[id] [int] IDENTITY(1,1) NOT NULL,

	[nomComarca] [nvarchar](50) NULL,

 CONSTRAINT [PK_comarques] PRIMARY KEY CLUSTERED 

(

	[id] ASC

)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, FILLFACTOR = 20) ON [PRIMARY]

) ON [PRIMARY]

GO

/****** Object:  Table [siadConstants].[pais]    Script Date: 31/07/2020 12:20:12 ******/

SET ANSI_NULLS ON

GO

SET QUOTED_IDENTIFIER ON

GO

CREATE TABLE [siadConstants].[pais](

	[codi] [int] NOT NULL,

	[abreviatura] [nvarchar](4) NULL,

	[pais] [nvarchar](100) NULL,

 CONSTRAINT [PK_PAIS] PRIMARY KEY CLUSTERED 

(

	[codi] ASC

)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, FILLFACTOR = 20) ON [PRIMARY]

) ON [PRIMARY]

GO

/****** Object:  Table [siadConstants].[provincies]    Script Date: 31/07/2020 12:20:12 ******/

SET ANSI_NULLS ON

GO

SET QUOTED_IDENTIFIER ON

GO

CREATE TABLE [siadConstants].[provincies](

	[codiProvincia] [int] NOT NULL,

	[nom] [varchar](30) NULL,

 CONSTRAINT [PK_provincies] PRIMARY KEY CLUSTERED 

(

	[codiProvincia] ASC

)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, FILLFACTOR = 20) ON [PRIMARY]

) ON [PRIMARY]

GO

ALTER TABLE [siadConstants].[pais] ADD  DEFAULT ((0)) FOR [codi]

GO

ALTER TABLE [siadConstants].[pais] ADD  DEFAULT (NULL) FOR [abreviatura]

GO

ALTER TABLE [siadConstants].[pais] ADD  DEFAULT (NULL) FOR [pais]

GO

ALTER TABLE [siadConstants].[provincies] ADD  DEFAULT ((0)) FOR [codiProvincia]

GO

ALTER TABLE [siadConstants].[provincies] ADD  DEFAULT (NULL) FOR [nom]

GO

ALTER TABLE [siad].[consulta]  WITH CHECK ADD  CONSTRAINT [FK_consulta_solicitant] FOREIGN KEY([CodiExpedient])

REFERENCES [siad].[solicitant] ([CodiExpedient])

GO

ALTER TABLE [siad].[consulta] CHECK CONSTRAINT [FK_consulta_solicitant]

GO

ALTER TABLE [siadConstants].[areaConsulta]  WITH CHECK ADD  CONSTRAINT [FK_areaConsulta_area] FOREIGN KEY([fkConsulta])

REFERENCES [siadConstants].[area] ([id])

GO

ALTER TABLE [siadConstants].[areaConsulta] CHECK CONSTRAINT [FK_areaConsulta_area]

GO

ALTER TABLE [siadConstants].[ciutats]  WITH CHECK ADD  CONSTRAINT [FK_ciutats_provincies] FOREIGN KEY([codiProvincia])

REFERENCES [siadConstants].[provincies] ([codiProvincia])

GO

ALTER TABLE [siadConstants].[ciutats] CHECK CONSTRAINT [FK_ciutats_provincies]

GO

