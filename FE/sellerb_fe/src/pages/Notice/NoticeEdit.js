import React, { useEffect, useState } from "react";
import { Link, useNavigate, useParams } from "react-router-dom";
import "./NoticeDetail.css";
import { Footer, NavBar } from "../../components/index";

import { DangerMediButton } from "../../components/Common/DangerMediButton";
import axios from "axios";

import {
  detailNoticeApi,
  modifyNoticeApi,
  delNoticeApi,
} from "../../api/noticeApi";

function NoticeEdit() {
  const navigate = useNavigate();
  const { id } = useParams();

  const [noticeData, setNoticeData] = useState({
    noticeSeq: id,
    noticeTitle: "",
    noticeContent: "",
  });

  const [bSeq, setBSeq] = useState(sessionStorage.getItem("brandSeq"));

  // 공지사항 내용 수정사항 반영
  const onChange = (e) => {
    e.preventDefault();

    const { value, name } = e.target;

    setNoticeData({
      ...noticeData,
      [name]: value,
    });
    console.log("noticeData:" + noticeData);
  };

  // 수정사항 제출 버튼
  const onEditSubmitBtn = async () => {
    setBSeq(parseInt(bSeq));
    console.log("bSeq : " + bSeq);

    const Info = {
      brandSeq: bSeq,
      noticeTitle: noticeData.noticeTitle,
      noticeContent: noticeData.noticeContent,
    };

    axios
      .put(`https://i7d105.p.ssafy.io/api/notice/${id}`, Info, {
        header: {
          "Content-Type": `multipart/form-data`,
        },
      })

      .then((res) => {
        console.log("수정 완료!");
        alert("수정 완료!");
        navigate("/manager/noticeList");
      })
      .catch((err) => {
        alert("수정 하는데 실패 했습니다!");
        console.log("수정 에러" + err);
      });
  };

  // 삭제 버튼
  const onDeleteSubmitBtn = () => {
    if (window.confirm("삭제하시겠습니까?")) {
      delNoticeApi(id)
        .then((res) => {
          console.log(res.data);
          navigate("/manager/noticeList");
        })
        .catch((err) => {
          console.log(err.data);
        });
    }
  };

  // 게시글의 상세정보 받아와서 setState
  useEffect(() => {
    detailNoticeApi(id)
      .then((res) => {
        setNoticeData(res.data);
        console.log("noticeData:" + noticeData);
      })
      .catch((e) => {
        console.log("err");
      });
  }, []);
  const parsingDate = (date) => {
    var parsedDate = new Date(date);
    var yyyy = parsedDate.getFullYear();
    var MM = parsedDate.getMonth() + 1;
    var dd = parsedDate.getDate();
    var hh = parsedDate.getHours() + 9;
    var mm = parsedDate.getMinutes();
    if (hh > 24) {
      hh = hh - 24;
      dd = dd + 1;
    }
    return (
      yyyy +
      "-" +
      addZero(MM) +
      "-" +
      addZero(dd) +
      "-" +
      addZero(hh) +
      ":" +
      addZero(mm)
    );
  };
  const addZero = (n) => {
    return n < 10 ? "0" + n : n;
  };
  return (
    <>
      <NavBar />
      <div className='consultant-main-image-wrapper'>
        <img
          src={`${process.env.PUBLIC_URL}/img/consultantMainPageImage.jpg`}
        />
      </div>
      <div className='board-wrap'>
        <div className='detail-head'>
          <div className='notice-detail-title'>공지사항</div>
          <div className='notice-detail-subTitle'>sellerB의 공지사항</div>
        </div>

        <div className='notice-detail-content-header'>
          <div className='content-header-row'>
            <div className='row-left'>제목</div>
            <input
              className='row-right'
              name='noticeTitle'
              onChange={onChange}
              defaultValue={noticeData.noticeTitle}
            />
          </div>
          <div className='content-header-row'>
            <div className='row-left'>작성일</div>
            <div className='row-right'>
              {parsingDate(noticeData.noticeRegDate)}
            </div>
          </div>

          <div className='notice-detail-content-detail'>
            <div>
              <pre>
                <input
                  style={{ width: "100%" }}
                  name='noticeContent'
                  defaultValue={noticeData.noticeContent}
                  onChange={onChange}
                />
              </pre>
            </div>
          </div>
          {/* content하단 */}
          <div className='notice-detail-bottom'>
            <button className='detail-button' onClick={onEditSubmitBtn}>
              수정완료
            </button>
            <DangerMediButton label='삭제하기' onClick={onDeleteSubmitBtn} />
          </div>
        </div>
      </div>
      <Footer />
    </>
  );
}

export default NoticeEdit;
