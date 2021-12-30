-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th10 21, 2021 lúc 03:53 PM
-- Phiên bản máy phục vụ: 10.4.21-MariaDB
-- Phiên bản PHP: 7.3.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `quanlytour`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chitiethoadon`
--

CREATE TABLE `chitiethoadon` (
  `MaHD` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `MaT` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `SoLuong` int(11) NOT NULL,
  `DonGia` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `chitiethoadon`
--

INSERT INTO `chitiethoadon` (`MaHD`, `MaT`, `SoLuong`, `DonGia`) VALUES
('HD1', 'T1', 1, 2890);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chitietphieunhap`
--

CREATE TABLE `chitietphieunhap` (
  `MaPN` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `MaT` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `SoLuong` int(10) UNSIGNED NOT NULL,
  `DonGia` float UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `chitietphieunhap`
--

INSERT INTO `chitietphieunhap` (`MaPN`, `MaT`, `SoLuong`, `DonGia`) VALUES
('PN1', 'T1', 10, 2890);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `doan`
--

CREATE TABLE `doan` (
  `MaDoan` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `TenDoan` varchar(70) COLLATE utf8_unicode_ci NOT NULL,
  `Diachi` varchar(200) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `doan`
--

INSERT INTO `doan` (`MaDoan`, `TenDoan`, `Diachi`) VALUES
('D1', 'Đoàn 1', 'Hà Nội'),
('D2', 'Đoàn 2', 'TP.HCM'),
('D3', 'Đoàn 3', 'TP.HCM'),
('D4', 'Đoàn 4', 'Hà Nội'),
('D5', 'Đoàn 5', 'Hà Nội'),
('D6', 'Đoàn 6', 'TP.HCM'),
('D7', 'Đoàn 7', 'TP.HCM'),
('D8', 'Đoàn 8', 'TP.HCM'),
('D9', 'Đoàn 9', 'TP.HCM'),
('D10', 'Đoàn 10', 'Hà Nội'),
('D11', 'Đoàn 11', 'TP.HCM'),
('D12', 'Đoàn 12', 'TP.HCM'),
('D13', 'Đoàn 13', 'Hà Nội'),
('D14', 'Đoàn 14', 'TP.HCM'),
('D15', 'Đoàn 15', 'TP.HCM'),
('D16', 'Đoàn 16', 'Hà Nội'),
('D17', 'Đoàn 17', 'TP.HCM');


-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `hoadon`
--

CREATE TABLE `hoadon` (
  `MaHD` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `MaNV` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `MaKH` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `MaKM` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `NgayLap` date NOT NULL,
  `GioLap` time NOT NULL,
  `TongTien` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `hoadon`
--

INSERT INTO `hoadon` (`MaHD`, `MaNV`, `MaKH`, `MaKM`, `NgayLap`, `GioLap`, `TongTien`) VALUES
('HD1', 'NV12', 'KH12', 'KM1', '2021-09-24', '21:43:30', 2890);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `khachhang`
--

CREATE TABLE `khachhang` (
  `MaKH` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `TenKH` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `DiaChi` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `SDT` varchar(15) COLLATE utf8_unicode_ci NOT NULL,
  `TrangThai` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `khachhang`
--

INSERT INTO `khachhang` (`MaKH`, `TenKH`, `DiaChi`, `SDT`, `TrangThai`) VALUES
('KH1', 'Nguyễn Thanh Sung', 'TP HCM', '0123456789', 0),
('KH10', 'Trần Hải Ngọc', 'Kiên Giang', '0905271941', 0),
('KH11', 'Nguyễn Xuân Diệu', 'TP HCM', '0301279552', 0),
('KH12', 'Trần Thân Thiện', 'Hà Nội', '0123617389', 1),
('KH13', 'Huỳnh Minh Mẫn', 'An Giang', '0389230581', 0),
('KH14', 'Trần Xuân Hạ Thu Đông', 'Long An', '0972136531', 0),
('KH15', 'Nguyễn Thị Nở', 'TP HCM', '0702571936', 0),
('KH16', 'Huỳnh Anh Em', 'Bến Tre', '0892383623', 0),
('KH17', 'Trần Thanh Hóa', 'TP HCM', '0702397442', 0),
('KH18', 'Huỳnh Minh Trung', 'TP HCM', '0120982736', 0),
('KH19', 'Trần Ngọc Ngà', 'TP HCM', '0702843627', 0),
('KH2', 'Nguyễn Thiên Thu', 'Huế', '0126461589', 1),
('KH20', 'Trần Thị Hoài Nhớ', 'TP HCM', '0126729137', 0),
('KH21', 'Nguyễn Văn Võ', 'Kiên Giang', '0723812935', 0),
('KH22', 'Huỳnh Lê Diệu Hân', 'Hà Nội', '0306279178', 1),
('KH3', 'Phan Thanh Tùng', 'Hà Nội', '0952612771', 0),
('KH4', 'Trần Thanh Sơn', 'Hải Phòng', '0120617231', 0),
('KH5', 'Trần Thanh Thái', 'Bến Tre', '0912385524', 1),
('KH6', 'Nguyễn Hồng Nhung', 'Huế', '0967263941', 0),
('KH7', 'Từ Ngọc Cảnh', 'Sóc Trăng', '0306172915', 0),
('KH8', 'Nguyễn Thiên Phụng', 'Vũng Tàu', '0703167293', 0),
('KH9', 'Nguyễn Diệu Ái', 'TP HCM', '0805178293', 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `khachsan`
--

CREATE TABLE `khachsan` (
  `MaKS` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `TenKS` varchar(70) COLLATE utf8_unicode_ci NOT NULL,
  `DiaChi` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `SDT` varchar(15) COLLATE utf8_unicode_ci NOT NULL,
  `Fax` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `khachsan`
--

INSERT INTO `khachsan` (`MaKS`, `TenKS`, `DiaChi`, `SDT`, `Fax`) VALUES
('KS1', 'Khách sạn Đông A', 'TP HCM', '024 3856 9367', '(84-28) 3 9951614'),
('KS2', 'Khách sạn First News', 'Australia', '028 3822 7979', '(84.28) 3822 4560'),
('KS3', 'Khách sạn Kim Đồng', 'Hà Nội', '0243 943 4490', '0243 8229085'),
('KS4', 'Khách sạn Nhã Nam', 'TP HCM', '0903244248', '(84-8) 38 443 034'),
('KS5', 'Khách sạn New Express', 'HongKong', '086 3745884', '415.863.9950'),
('KS6', 'Khách sạn Faded', 'TP.HCM', '(84. 28) 382279', '(84. 28) 3822 4560'),
('KS7', 'Khách sạn Thái Hà', 'Hà Nội', '024 3793 0480', '0243 8229469'),
('KS8', 'Khách sạn New Touw', 'TP HCM', '(84.028) 393162', '(84.028) 38437450');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `khuyenmai`
--

CREATE TABLE `khuyenmai` (
  `MaKM` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `TenKM` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `DieuKienKM` float NOT NULL,
  `PhanTramKM` float NOT NULL,
  `NgayBD` date DEFAULT NULL,
  `NgayKT` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `khuyenmai`
--

INSERT INTO `khuyenmai` (`MaKM`, `TenKM`, `DieuKienKM`, `PhanTramKM`, `NgayBD`, `NgayKT`) VALUES
('KM1', 'Không khuyến mãi', 0, 0, '2021-04-01', '2022-04-01'),
('KM2', 'Giảm giá nhân ngày 30/4', 5, 5, '2021-04-28', '2021-05-02'),
('KM3', 'Giảm giá 1/5', 20, 7.5, '2022-05-01', '2022-05-08'),
('KM4', 'Giảm giá tết', 15, 5, '2022-01-01', '2022-02-01'),
('KM5', 'Khuyến mãi xả hàng', 100, 15, '2021-12-01', '2021-12-30');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `loaitour`
--

CREATE TABLE `loaitour` (
  `MaLT` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `TenLT` varchar(70) COLLATE utf8_unicode_ci NOT NULL,
  `Mota` varchar(200) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `loaitour`
--

INSERT INTO `loaitour` (`MaLT`, `TenLT`, `Mota`) VALUES
('LT1', 'Tour ngắn ngày', 'Các loại tour đi trong 2 ngày 2 đêm trở xuống'),
('LT2', 'Tour 3-5 ngày', 'Các loại tour đi từ 3 ngày đến 5 ngày'),
('LT3', 'Tour dài ngày', 'Các loại tour du lịch trên 5 ngày'),
('LT4', 'Tour quốc tế', 'Các loại tour đi qua các nước khác'),
('LT5', 'Tour gần TPHCM', 'Các loại tour đi gần vùng thành phố HCM'),
('LT6', 'Tour cách TPHCM bán kính 500km', 'Các loại tour đi xa thành phố bán kính 500km'),
('LT7', 'Tour gần Hà Nội', 'Các loại tour đi gần vùng thủ đô Hà Nội'),
('LT8', 'Tour cách Hà Nội bán kính 500km', 'Các loại tour đi xa thủ đô Hà Nội bán kính 500km'),
('LT9', 'Tour gần Quảng Nam', 'Các loại tour đi gần vùng thành phố Quảng Nam'),
('LT10', 'Tour cách Quảng Nam 500km', 'Các loại tour đi xa thành phố Quảng Nam bán kính 500km'),
('LT11', 'Tour gần Nha Trang', 'Các loại tour đi gần Thành Phố biển Nha Trang');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `nhanvien`
--

CREATE TABLE `nhanvien` (
  `MaNV` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `TenNV` text COLLATE utf8_unicode_ci NOT NULL,
  `NgaySinh` date NOT NULL,
  `DiaChi` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `SDT` varchar(15) COLLATE utf8_unicode_ci NOT NULL,
  `TrangThai` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `nhanvien`
--

INSERT INTO `nhanvien` (`MaNV`, `TenNV`, `NgaySinh`, `DiaChi`, `SDT`, `TrangThai`) VALUES
('NV1', 'Phạm Anh Tuấn', '1999-04-05', 'Đắk Lắk', '0145647854', 0),
('NV10', 'Nguyễn Thị Hồng Hạnh', '2001-11-29', 'Bến Tre', '01262368193', 0),
('NV11', 'Phan Thị Hồng Trinh', '2001-12-11', 'Nghệ An', '0366227168', 0),
('NV12', 'Nguyễn Mai Dương', '2000-12-06', 'Bình PHước', '0981578293', 0),
('NV13', 'Lê Công Huynh', '2001-09-12', 'Sóc Trăng', '0977232173', 0),
('NV14', 'Lê Hồng Hoa', '1992-08-13', 'TP HCM', '0805126735', 0),
('NV15', 'Nguyễn Thị My', '1992-12-30', 'Hà Nội', '0703689147', 0),
('NV16', 'Trương Thị Hồng Huệ', '2001-11-29', 'Quảng Bình', '0825719263', 0),
('NV17', 'Trương Minh Hải', '1992-01-16', 'Thanh Hoá', '0123691368', 0),
('NV18', 'Nguyễn Thị Cẩm Duyên', '1995-11-03', 'TP HCM', '0120984178', 0),
('NV19', 'Lê Thanh Quang', '1995-04-19', 'Huế', '0956146728', 0),
('NV2', 'Trần Văn Hi', '2000-04-05', 'TP HCM', '0123456489', 0),
('NV20', 'Đặng Anh Tuấn', '1997-02-14', 'Đắk Lắk', '0702536184', 0),
('NV21', 'Huỳnh Công Thành', '1996-11-20', 'Long An', '0709123175', 0),
('NV22', 'Huỳnh Thị Hồng Hương', '1994-11-27', 'TP HCM', '0912635198', 0),
('NV23', 'Lê Nguyên Trung', '2001-03-14', 'Long An', '0123671823', 0),
('NV24', 'Trương Thanh Dũng', '1997-10-28', 'Đồng Tháp', '0981237651', 0),
('NV3', 'Nguyễn Hữu An', '2001-06-15', 'TP HCM', '0128456786', 1),
('NV4', 'Lê Anh Tuấn', '1999-04-05', 'Đắk Lắk', '01207764668', 0),
('NV5', 'Trương Minh Hải', '2001-11-29', 'Quảng Bình', '0367756753', 0),
('NV6', 'Nguyễn Hải Âu', '1992-04-24', 'Huế', '0364198226', 0),
('NV7', 'Hoàng Thanh Hùng', '1989-11-13', 'Long An', '0276886265', 0),
('NV8', 'Trịnh Văn Công', '1990-07-16', 'Tiền Giang', '0392656931', 0),
('NV9', 'Nguyễn Minh Hải', '2001-11-29', 'Quảng Bình', '0977268398', 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `phanquyen`
--

CREATE TABLE `phanquyen` (
  `MaQuyen` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `TenQuyen` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `ChiTietQuyen` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `phanquyen`
--

INSERT INTO `phanquyen` (`MaQuyen`, `TenQuyen`, `ChiTietQuyen`) VALUES
('Q1', 'Quản lý', 'xemTour xemLoaiTour xemTacGia xemĐoan xemHoaDon qlNhanVien qlKhachHang xemPhieuNhap xemKS qlTaiKhoan qlQuyen'),
('Q2', 'Nhân viên Bán hàng', 'qlBanHang xemTour xemLoaiTour xemKhachSan xemĐoan xemHoaDon xemNhanVien xemKhachHang'),
('Q3', 'Phụ Bán Hàng', 'qlBanHang xemTour xemKhuyenMai xemKhachHang'),
('Q4', 'Admin', 'qlBanHang qlNhapHang qlTour qlLoaiTour qlĐoan qlHoaDon qlKhuyenMai qlNhanVien qlKhachHang qlPhieuNhap qlKS qlTaiKhoan qlQuyen'),
('Q5', 'Nhân viên Nhập hàng', 'qlNhapHang xemTour xemLoaiTour xemNhanVien qlPhieuNhap qlKS');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `phieunhap`
--

CREATE TABLE `phieunhap` (
  `MaPN` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `MaKS` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `MaNV` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `NgayNhap` date NOT NULL,
  `GioNhap` time NOT NULL,
  `TongTien` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `phieunhap`
--

INSERT INTO `phieunhap` (`MaPN`, `MaKS`, `MaNV`, `NgayNhap`, `GioNhap`, `TongTien`) VALUES
('PN1', 'KS1', 'NV12', '2021-05-10', '11:19:53', 20000);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `taikhoan`
--

CREATE TABLE `taikhoan` (
  `TenTaiKhoan` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `MatKhau` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `MaNV` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `MaQuyen` varchar(10) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `taikhoan`
--

INSERT INTO `taikhoan` (`TenTaiKhoan`, `MatKhau`, `MaNV`, `MaQuyen`) VALUES
('admin', 'admin', 'NV12', 'Q4'),
('AnhTuanAdmin', 'anhtuan', 'NV4', 'Q3'),
('AnhTuanSeller', 'anhtuan', 'NV1', 'Q2'),
('GiaBao', 'giabao', 'NV11', 'Q4'),
('MinhHaiNH', 'minhhai', 'NV5', 'Q5'),
('NguyenTrungBH', 'nguyentrung', 'NV23', 'Q3'),
('NhanVien', 'nv', 'NV20', 'Q2'),
('Quan Ly', 'quanly', 'NV9', 'Q1'),
('Trung', '20trung01', 'NV3', 'Q2');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tour`
--

CREATE TABLE `tour` (
  `MaT` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `MaLT` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `TenT` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `DonGia` float NOT NULL,
  `SoLuong` int(100) UNSIGNED NOT NULL DEFAULT 1,
  `HinhAnh` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `TrangThai` int(11) NOT NULL,
  `MaDoan` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `tour`
--

INSERT INTO `tour` (`MaT`, `MaLT`, `TenT`, `DonGia`, `SoLuong`, `HinhAnh`, `TrangThai`, `MaDoan`) VALUES
('T1', 'LT4', 'Tour du lịch Australia ', 2890, 90, 'Australia.jpg', 0, 'D1'),
('T2', 'LT1', 'Tour du lịch Phú Quốc', 2300, 90, 'Bienphuquoc.jpg', 0, 'D2'),
('T3', 'LT2', 'Tour du lịch Bình Ba', 1890, 99, 'BinhBa.png', 0, 'D3'),
('T4', 'LT3', 'Tour du lịch Châu Đốc', 699, 99 , 'ChauDoc.png', 0, 'D4'),
('T5', 'LT6', 'Tour du lịch Nam Du', 2390, 99, 'chaudocnamdu3n2d.png', 0, 'D5'),
('T6', 'LT1', 'Tour du lịch Côn Đảo', 2690, 99, 'condao2n2d.png', 0, 'D6'),
('T7', 'LT9', 'Tour du lịch Đà Lạt', 2190, 99, 'dalat4n3d.png', 0, 'D7'),
('T8', 'LT8', 'Tour du lịch Đà lạt Giường Nằm', 2190, 99, 'dalatgiuongnam3n3D.png', 0, 'D8'),
('T9', 'LT3', 'Tour du lịch Đảo Cô Tô', 2450, 99, 'Daocoto.jpg', 0, 'D9'),
('T10', 'LT7', 'Tour du lịch Nha Trang', 3580, 44, 'nhatrang2n2d.png', 0, 'D10'),
('T11', 'LT2', 'Tour du lịch Phan Thiết', 1200, 70, 'Phanthiet.png', 0, 'D11'),
('T12', 'LT4', 'Tour du lịch Hồng Kông', 10500, 50, 'Hongkong.jpg', 0, 'D12'),
('T13', 'LT2', 'Tour du lịch Phan Thiết', 1290, 50, 'Phanthiet2n1d.jpg', 0, 'D13'),
('T14', 'LT4', 'Tour du lịch Trung Quốc', 15390, 55, 'Trungquoc.jpg', 0, 'D14'),
('T15', 'LT5', 'Tour du lịch Tà Đùng', 1990, 99, 'tadungdalat3n3d-.png', 0, 'D15'),
('T16', 'LT4', 'Tour du lịch Sydney', 3980, 56, 'sydney_australia.jpg', 0, 'D16'),
('T17', 'LT4', 'Tour du lịch PhiLipine', 3890 , 57, 'daophilip.jpg', 0, 'D17');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `chitiethoadon`
--
ALTER TABLE `chitiethoadon`
  ADD KEY `MaT` (`MaT`),
  ADD KEY `MaHD` (`MaHD`);

--
-- Chỉ mục cho bảng `chitietphieunhap`
--
ALTER TABLE `chitietphieunhap`
  ADD KEY `MaT` (`MaT`),
  ADD KEY `MaPN` (`MaPN`);

--
-- Chỉ mục cho bảng `doan`
--
ALTER TABLE `doan`
  ADD PRIMARY KEY (`MaDoan`);

--
-- Chỉ mục cho bảng `hoadon`
--
ALTER TABLE `hoadon`
  ADD PRIMARY KEY (`MaHD`),
  ADD KEY `MaNV` (`MaNV`),
  ADD KEY `MaKH` (`MaKH`),
  ADD KEY `MaKM` (`MaKM`);

--
-- Chỉ mục cho bảng `khachhang`
--
ALTER TABLE `khachhang`
  ADD PRIMARY KEY (`MaKH`);

--
-- Chỉ mục cho bảng `khachsan`
--
ALTER TABLE `khachsan`
  ADD PRIMARY KEY (`MaKS`);

--
-- Chỉ mục cho bảng `khuyenmai`
--
ALTER TABLE `khuyenmai`
  ADD PRIMARY KEY (`MaKM`);

--
-- Chỉ mục cho bảng `loaitour`
--
ALTER TABLE `loaitour`
  ADD PRIMARY KEY (`MaLT`);

--
-- Chỉ mục cho bảng `nhanvien`
--
ALTER TABLE `nhanvien`
  ADD PRIMARY KEY (`MaNV`);

--
-- Chỉ mục cho bảng `phanquyen`
--
ALTER TABLE `phanquyen`
  ADD PRIMARY KEY (`MaQuyen`);

--
-- Chỉ mục cho bảng `phieunhap`
--
ALTER TABLE `phieunhap`
  ADD PRIMARY KEY (`MaPN`),
  ADD KEY `MaKS` (`MaKS`),
  ADD KEY `MaNV` (`MaNV`);

--
-- Chỉ mục cho bảng `taikhoan`
--
ALTER TABLE `taikhoan`
  ADD PRIMARY KEY (`TenTaiKhoan`),
  ADD KEY `MaQuyen` (`MaQuyen`),
  ADD KEY `MaNV` (`MaNV`);

--
-- Chỉ mục cho bảng `tour`
--
ALTER TABLE `tour`
  ADD PRIMARY KEY (`MaT`),
  ADD KEY `LoaiT` (`MaLT`),
  ADD KEY `doan` (`MaDoan`);

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `chitiethoadon`
--
ALTER TABLE `chitiethoadon`
  ADD CONSTRAINT `chitiethoadon_ibfk_1` FOREIGN KEY (`MaHD`) REFERENCES `hoadon` (`MaHD`) ON UPDATE CASCADE,
  ADD CONSTRAINT `chitiethoadon_ibfk_2` FOREIGN KEY (`MaT`) REFERENCES `tour` (`MaT`) ON UPDATE CASCADE;

--
-- Các ràng buộc cho bảng `chitietphieunhap`
--
ALTER TABLE `chitietphieunhap`
  ADD CONSTRAINT `chitietphieunhap_ibfk_2` FOREIGN KEY (`MaT`) REFERENCES `tour` (`MaT`) ON UPDATE CASCADE,
  ADD CONSTRAINT `chitietphieunhap_ibfk_3` FOREIGN KEY (`MaPN`) REFERENCES `phieunhap` (`MaPN`) ON UPDATE CASCADE;

--
-- Các ràng buộc cho bảng `hoadon`
--
ALTER TABLE `hoadon`
  ADD CONSTRAINT `hoadon_ibfk_1` FOREIGN KEY (`MaKH`) REFERENCES `khachhang` (`MaKH`) ON UPDATE CASCADE,
  ADD CONSTRAINT `hoadon_ibfk_2` FOREIGN KEY (`MaNV`) REFERENCES `nhanvien` (`MaNV`) ON UPDATE CASCADE,
  ADD CONSTRAINT `khuyenmai_ibfk_3` FOREIGN KEY (`MaKM`) REFERENCES `khuyenmai` (`MaKM`) ON UPDATE CASCADE;

--
-- Các ràng buộc cho bảng `phieunhap`
--
ALTER TABLE `phieunhap`
  ADD CONSTRAINT `phieunhap_ibfk_1` FOREIGN KEY (`MaKS`) REFERENCES `khachsan` (`MaKS`) ON UPDATE CASCADE,
  ADD CONSTRAINT `phieunhap_ibfk_2` FOREIGN KEY (`MaNV`) REFERENCES `nhanvien` (`MaNV`) ON UPDATE CASCADE;

--
-- Các ràng buộc cho bảng `taikhoan`
--
ALTER TABLE `taikhoan`
  ADD CONSTRAINT `taikhoan_ibfk_2` FOREIGN KEY (`MaNV`) REFERENCES `nhanvien` (`MaNV`) ON UPDATE CASCADE,
  ADD CONSTRAINT `taikhoan_ibfk_3` FOREIGN KEY (`MaQuyen`) REFERENCES `phanquyen` (`MaQuyen`) ON UPDATE CASCADE;

--
-- Các ràng buộc cho bảng `tour`
--
ALTER TABLE `tour`
  ADD CONSTRAINT `Tour_ibfk_1` FOREIGN KEY (`MaLT`) REFERENCES `loaitour` (`MaLT`) ON UPDATE CASCADE,
  ADD CONSTRAINT `doan_ibfk_2` FOREIGN KEY (`MaDoan`) REFERENCES `doan` (`MaDoan`) ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
