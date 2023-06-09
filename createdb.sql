USE [master]
GO
/****** Object:  Database [Music]    Script Date: 6/5/2023 11:06:56 PM ******/
CREATE DATABASE [Music]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'Music', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.SQLEXPRESS\MSSQL\DATA\Music.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'Music_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.SQLEXPRESS\MSSQL\DATA\Music_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [Music].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [Music] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [Music] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [Music] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [Music] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [Music] SET ARITHABORT OFF 
GO
ALTER DATABASE [Music] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [Music] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [Music] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [Music] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [Music] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [Music] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [Music] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [Music] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [Music] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [Music] SET  DISABLE_BROKER 
GO
ALTER DATABASE [Music] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [Music] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [Music] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [Music] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [Music] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [Music] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [Music] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [Music] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [Music] SET  MULTI_USER 
GO
ALTER DATABASE [Music] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [Music] SET DB_CHAINING OFF 
GO
ALTER DATABASE [Music] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [Music] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [Music] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [Music] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
ALTER DATABASE [Music] SET QUERY_STORE = ON
GO
ALTER DATABASE [Music] SET QUERY_STORE (OPERATION_MODE = READ_WRITE, CLEANUP_POLICY = (STALE_QUERY_THRESHOLD_DAYS = 30), DATA_FLUSH_INTERVAL_SECONDS = 900, INTERVAL_LENGTH_MINUTES = 60, MAX_STORAGE_SIZE_MB = 1000, QUERY_CAPTURE_MODE = AUTO, SIZE_BASED_CLEANUP_MODE = AUTO, MAX_PLANS_PER_QUERY = 200, WAIT_STATS_CAPTURE_MODE = ON)
GO
USE [Music]
GO
/****** Object:  Table [Album]    Script Date: 6/5/2023 11:06:56 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [Album](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[NameAlbum] [nvarchar](255) NOT NULL,
	[Artist] [nvarchar](255) NOT NULL
) ON [PRIMARY]
GO
/****** Object:  Table [AlbumDetails]    Script Date: 6/5/2023 11:06:56 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [AlbumDetails](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[AlbumId] [int] NOT NULL,
	[SongId] [int] NOT NULL
) ON [PRIMARY]
GO
/****** Object:  Table [Song]    Script Date: 6/5/2023 11:06:56 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [Song](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Title] [nvarchar](255) NOT NULL,
	[Genre] [nvarchar](255) NOT NULL,
	[ReleaseYear] [int] NULL,
	[Duration] [real] NULL,
PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [Album] ON 

INSERT [Album] ([Id], [NameAlbum], [Artist]) VALUES (2, N'The night', N'Anvicii')
SET IDENTITY_INSERT [Album] OFF
GO
SET IDENTITY_INSERT [AlbumDetails] ON 

INSERT [AlbumDetails] ([Id], [AlbumId], [SongId]) VALUES (4, 2, 18)
SET IDENTITY_INSERT [AlbumDetails] OFF
GO
SET IDENTITY_INSERT [Song] ON 

INSERT [Song] ([Id], [Title], [Genre], [ReleaseYear], [Duration]) VALUES (18, N'The night', N'pop', 2017, 3.5)
SET IDENTITY_INSERT [Song] OFF
GO
/****** Object:  StoredProcedure [InsertAlbumDetails]    Script Date: 6/5/2023 11:06:56 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [InsertAlbumDetails]
    @AlbumId int ,
    @SongId int 
AS 
BEGIN
    -- Chèn dữ liệu vào bảng Song
    Insert into AlbumDetails (AlbumId,SongId) 
    values (@AlbumId, @SongId);
END;
GO
/****** Object:  StoredProcedure [InsertSong]    Script Date: 6/5/2023 11:06:56 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [InsertSong]
    @Title nvarchar(255),
    @Genre nvarchar(255),
    @ReleaseYear int,
    @Duration real,
    @nameAlbum nvarchar(255),
    @Artist nvarchar(255)
AS 
BEGIN
    -- Chèn dữ liệu vào bảng Song
    Insert into Song (Title, Genre, ReleaseYear, Duration) 
    values (@Title, @Genre, @ReleaseYear, @Duration);

    -- Lấy ID của bản ghi vừa chèn trong bảng Song
    DECLARE @InsertedID int;
    SET @InsertedID = SCOPE_IDENTITY();

    -- Truy vấn Album để lấy thông tin cần thiết
    Select Id
    from Album
    where NameAlbum = @nameAlbum and Artist = @Artist;

    -- Trả về ID của bản ghi vừa chèn trong bảng Song
    SELECT @InsertedID AS InsertedID;
END;
GO
/****** Object:  StoredProcedure [ListSong]    Script Date: 6/5/2023 11:06:56 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
Create Procedure  [ListSong]
AS
Begin
Select s.Title,a.Artist 
	from Song s inner join AlbumDetails ad 
	on s.Id=ad.SongId inner join Album a 
	on ad.AlbumId=a.Id;
END;
GO
/****** Object:  StoredProcedure [SearchSongByArtist]    Script Date: 6/5/2023 11:06:56 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [SearchSongByArtist]
    @Artist nvarchar(255)
AS 
BEGIN
    Select s.Title,s.Genre,s.ReleaseYear,s.Duration,a.NameAlbum,a.Artist 
	from Song s inner join AlbumDetails ad 
	on s.Id=ad.SongId inner join Album a 
	on ad.AlbumId=a.Id where a.Artist=@Artist;
END;
GO
/****** Object:  StoredProcedure [SearchSongByTitle]    Script Date: 6/5/2023 11:06:56 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [SearchSongByTitle]
    @Title nvarchar(255)
AS 
BEGIN
    Select s.Title,s.Genre,s.ReleaseYear,s.Duration,a.NameAlbum,a.Artist 
	from Song s inner join AlbumDetails ad 
	on s.Id=ad.SongId inner join Album a 
	on ad.AlbumId=a.Id where s.Title=@Title;
END;
GO
/****** Object:  StoredProcedure [UpdateSong]    Script Date: 6/5/2023 11:06:56 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
Create Procedure [UpdateSong]
@oldTitle nvarchar(255),
@Title nvarchar(255),
    @Genre nvarchar(255),
    @ReleaseYear int,
    @Duration real
AS 
BEGIN
Update Song set Title=@Title, Genre=@Genre, ReleaseYear=@ReleaseYear, Duration=@Duration where Title=@oldTitle;
END;
GO
USE [master]
GO
ALTER DATABASE [Music] SET  READ_WRITE 
GO
