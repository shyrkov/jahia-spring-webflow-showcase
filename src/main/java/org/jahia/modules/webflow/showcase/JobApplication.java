/**
 * This file is part of Jahia, next-generation open source CMS:
 * Jahia's next-generation, open source CMS stems from a widely acknowledged vision
 * of enterprise application convergence - web, search, document, social and portal -
 * unified by the simplicity of web content management.
 *
 * For more information, please visit http://www.jahia.com.
 *
 * Copyright (C) 2002-2014 Jahia Solutions Group SA. All rights reserved.
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301, USA.
 *
 * As a special exception to the terms and conditions of version 2.0 of
 * the GPL (or any later version), you may redistribute this Program in connection
 * with Free/Libre and Open Source Software ("FLOSS") applications as described
 * in Jahia's FLOSS exception. You should have received a copy of the text
 * describing the FLOSS exception, and it is also available here:
 * http://www.jahia.com/license
 *
 * Commercial and Supported Versions of the program (dual licensing):
 * alternatively, commercial and supported versions of the program may be used
 * in accordance with the terms and conditions contained in a separate
 * written agreement between you and Jahia Solutions Group SA.
 *
 * If you are unsure which license is appropriate for your use,
 * please contact the sales department at sales@jahia.com.
 */
package org.jahia.modules.webflow.showcase;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Pattern;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.multipart.MultipartFile;

/**
 * Job application model object.
 * 
 * @author Sergiy Shyrkov
 */
public class JobApplication implements Serializable {

    public static class UploadedFile implements Serializable {

        private static final long serialVersionUID = 6716048385027138182L;

        private String contentType;

        private File file;

        private String originalFilename;

        public UploadedFile(String originalFilename, String contentType, File file) {
            super();
            this.originalFilename = originalFilename;
            this.contentType = contentType;
            this.file = file;
        }

        public String getContentType() {
            return contentType;
        }

        public File getFile() {
            return file;
        }

        public String getOriginalFilename() {
            return originalFilename;
        }
    }

    private static final long serialVersionUID = -1509641379193633593L;

    private UploadedFile coverLetter;

    @NotEmpty
    @Email
    private String email;

    private int experience;

    @NotEmpty
    private String firstname;

    @NotEmpty
    private String lastname;

    private String phone;

    private UploadedFile resume;

    private int salary;

    @DateTimeFormat(iso = ISO.DATE)
    private Date startingDate;

    private UploadedFile copy(MultipartFile multipartFile) throws IOException {
        UploadedFile uploaded = null;
        if (multipartFile != null && !multipartFile.isEmpty()) {
            String originalFilename = multipartFile.getOriginalFilename();
            File tmp = File.createTempFile(FilenameUtils.getBaseName(originalFilename),
                    "." + FilenameUtils.getExtension(originalFilename));
            multipartFile.transferTo(tmp);
            uploaded = new UploadedFile(originalFilename, multipartFile.getContentType(), tmp);
        }

        return uploaded;
    }

    public UploadedFile getCoverLetter() {
        return coverLetter;
    }

    @Pattern(regexp = "application/.*pdf")
    public String getCoverLetterContentType() {
        return coverLetter != null ? coverLetter.getContentType() : "application/pdf";
    }

    @Max(value = 1 * 1024 * 1024L)
    public long getCoverLetterLength() {
        return coverLetter != null ? coverLetter.getFile().length() : 0;
    }

    public String getEmail() {
        return email;
    }

    public int getExperience() {
        return experience;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getPhone() {
        return phone;
    }

    public UploadedFile getResume() {
        return resume;
    }

    @Pattern(regexp = "application/.*pdf")
    public String getResumeContentType() {
        return resume != null ? resume.getContentType() : "application/pdf";
    }

    @Max(value = 10 * 1024 * 1024L)
    public long getResumeLength() {
        return resume != null ? resume.getFile().length() : 0;
    }

    public int getSalary() {
        return salary;
    }

    public Date getStartingDate() {
        return startingDate;
    }

    public void setCoverLetter(MultipartFile coverLetter) throws IOException {
        if (coverLetter != null && !coverLetter.isEmpty()) {
            this.coverLetter = copy(coverLetter);
        }
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setResume(MultipartFile vita) throws IOException {
        if (vita != null && !vita.isEmpty()) {
            this.resume = copy(vita);
        }
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void setStartingDate(Date startingDate) {
        this.startingDate = startingDate;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
