import React, { useState, useEffect } from "react";
import { Link, useNavigate, useParams } from "react-router-dom";
import "./ManagerMyPage.css";
import NavBar from "../../../components/Common/NavBar/NavBar";
import Footer from "../../../components/Common/Footer/Footer";
import Button from "@mui/material/Button";
import TextField from "@mui/material/TextField";
import { getManagerInfoApi } from "../../../api/managerApi";
import { indigo } from "@mui/material/colors";

import axios from "axios";
import fileapi from "../../../api/fileapi";
import { modifyManagerApi } from '../../../api/userApi'

import { SmallButton } from "../../../components/Common/SmallButton";
import { MediButton } from "../../../components/Common/MediButton";

function ManagerMyPageEdit() {
  const { id } = useParams();
  const navigate = useNavigate();
  const [managerSeq, setManagerSeq] = useState(sessionStorage.getItem("seq"));
  const [brandNameKor, setBrandNameKor] = useState("");

  const [manager, setManager] = useState([]);
  const [editPass, setEditPass] = useState();

  useEffect(() => {
    if (sessionStorage.getItem("accessToken") === null) {
      alert("접근 권한이 없습니다.");
      navigate("/");
    }
    console.log("useEffect:" + sessionStorage.getItem("seq"));
    getManagerInfoApi(managerSeq)
      .then((res) => {
        setManager(res.data);
        setBrandNameKor(res.data.brand.brandNameKor);
      })
      .catch((err) => {
        console.log(err);
      });
  }, []);

  const onHandleChange = (e) => {
    e.preventDefault();
    const { value, name } = e.target;
    setManager({
      ...manager,
      [name]: value,
    });
  };

  // editPass Handling
  const onChangePass = (e) => {
    setEditPass(e.target.value);
  };

  // 수정완료
  const onEditCompleteBtn = async () => {
    const EditInfo = {
      managerPass: editPass,
      managerTel: manager.managerTel,
      managerEmail: manager.managerEmail,
      managerImageUrl: manager.managerImageUrl,
    };

    modifyManagerApi(EditInfo, managerSeq)
    .then((res)=>{
      console.log(JSON.stringify(res.data));
      console.log("success");
    })
    .catch((err)=>{

      console.log("Error");
    })

    navigate("/main");

  };

  return (
    <>
    <NavBar />
    <div className="manager-profile-title">매니저 프로필</div>
        <div className="consultant-profile-container">
        <div className="manager-profile-wrapper">
          <div className="con-mypage-left-wrapper">
            <img
              className='con-mypage-default-img'
              alt='#'
              src={manager.managerImageUrl}
            />
          </div>

          <div className='con-mypage-profile-left'>
            <div className='con-profile-element'>
              <p>아이디</p>
              <div>{manager.managerId}</div>
            </div>
            <div className='con-profile-element'>
              <p>이름</p>
              <div>{manager.managerName}</div>
            </div>
            <div className='con-profile-element'>
              <p>Email</p>
              <input
                defaultValue={manager.managerEmail}
                name='managerEmail'
                onChange={onHandleChange}
              />
            </div>
            <div className='con-profile-element'>
              <p>Pnum</p>
              <input
                defaultValue={manager.managerTel}
                name='managerTel'
                onChange={onHandleChange}
              />
            </div>
            <div className="con-profile-element">
            <p>비밀번호</p>
              <input type="password" value={editPass} onChange={onChangePass} />
          </div>         
            <div style={{ display: "flex", marginLeft: "5px", marginTop:"10%" }}>
              <MediButton label="수정완료" onClick={onEditCompleteBtn} />

            </div>
          </div>

        </div>
      </div>

      <Footer />
    </>
  );
}

export default ManagerMyPageEdit;
