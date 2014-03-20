/**
 * ==========================================================================================
 * =                        DIGITAL FACTORY v7.0 - Community Distribution                   =
 * ==========================================================================================
 *
 *     Rooted in Open Source CMS, Jahia's Digital Industrialization paradigm is about
 *     streamlining Enterprise digital projects across channels to truly control
 *     time-to-market and TCO, project after project.
 *     Putting an end to "the Tunnel effect", the Jahia Studio enables IT and
 *     marketing teams to collaboratively and iteratively build cutting-edge
 *     online business solutions.
 *     These, in turn, are securely and easily deployed as modules and apps,
 *     reusable across any digital projects, thanks to the Jahia Private App Store Software.
 *     Each solution provided by Jahia stems from this overarching vision:
 *     Digital Factory, Workspace Factory, Portal Factory and eCommerce Factory.
 *     Founded in 2002 and headquartered in Geneva, Switzerland,
 *     Jahia Solutions Group has its North American headquarters in Washington DC,
 *     with offices in Chicago, Toronto and throughout Europe.
 *     Jahia counts hundreds of global brands and governmental organizations
 *     among its loyal customers, in more than 20 countries across the globe.
 *
 *     For more information, please visit http://www.jahia.com
 *
 * JAHIA'S DUAL LICENSING - IMPORTANT INFORMATION
 * ============================================
 *
 *     Copyright (C) 2002-2014 Jahia Solutions Group SA. All rights reserved.
 *
 *     THIS FILE IS AVAILABLE UNDER TWO DIFFERENT LICENSES:
 *     1/GPL OR 2/JSEL
 *
 *     1/ GPL
 *     ==========================================================
 *
 *     IF YOU DECIDE TO CHOSE THE GPL LICENSE, YOU MUST COMPLY WITH THE FOLLOWING TERMS:
 *
 *     "This program is free software; you can redistribute it and/or
 *     modify it under the terms of the GNU General Public License
 *     as published by the Free Software Foundation; either version 2
 *     of the License, or (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program; if not, write to the Free Software
 *     Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301, USA.
 *
 *     As a special exception to the terms and conditions of version 2.0 of
 *     the GPL (or any later version), you may redistribute this Program in connection
 *     with Free/Libre and Open Source Software ("FLOSS") applications as described
 *     in Jahia's FLOSS exception. You should have received a copy of the text
 *     describing the FLOSS exception, and it is also available here:
 *     http://www.jahia.com/license"
 *
 *     2/ JSEL - Commercial and Supported Versions of the program
 *     ==========================================================
 *
 *     IF YOU DECIDE TO CHOOSE THE JSEL LICENSE, YOU MUST COMPLY WITH THE FOLLOWING TERMS:
 *
 *     Alternatively, commercial and supported versions of the program - also known as
 *     Enterprise Distributions - must be used in accordance with the terms and conditions
 *     contained in a separate written agreement between you and Jahia Solutions Group SA.
 *
 *     If you are unsure which license is appropriate for your use,
 *     please contact the sales department at sales@jahia.com.
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
